package com.example.servicedemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class RunningService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    // This is triggered when another android component sends an intent to this service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val channelId = createNotificationChannel()
        val notification = Notification.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is Active")
            .setContentText("Elapsed Time: 00:50")
            .build()
        startForeground(1, notification)
    }

    private fun createNotificationChannel(): String {
        val channelId = "running_service_channel"
        val channelName = "Running Service Channel"
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )
        return channelId
    }

    enum class Actions {
        START, STOP
    }

}