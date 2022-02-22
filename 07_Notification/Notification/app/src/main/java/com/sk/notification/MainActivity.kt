package com.sk.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class MainActivity : AppCompatActivity() {
    private val channelId = "com.sk.notification.channelfirst"
    private var notificationManager: NotificationManager? = null
    private val KEY_REPLY = "key_reply"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelId, "Demo", "demo channel")
        findViewById<Button>(R.id.button).setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification() {
        val notificationId = 12
        val tapResultIntent = Intent(this, SecondActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        //details action button
        val intentDetails = Intent(this, DetailsActivity::class.java)
        val pendingIntentDetails =
            PendingIntent.getActivity(this, 0, intentDetails, PendingIntent.FLAG_UPDATE_CURRENT)
        val detailsActivityAction: NotificationCompat.Action =
            NotificationCompat.Action.Builder(0, "Details", pendingIntentDetails).build()


        //settings action button
        val intentSettings = Intent(this, SettingsActivity::class.java)
        val pendingIntentSettings =
            PendingIntent.getActivity(this, 0, intentSettings, PendingIntent.FLAG_UPDATE_CURRENT)
        val settingsActivityAction: NotificationCompat.Action =
            NotificationCompat.Action.Builder(0, "Settings", pendingIntentSettings).build()

        //reply action
        val remoteInput = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insert your name")
            build()
        }
        val replyAction: NotificationCompat.Action =
            NotificationCompat.Action.Builder(0, "REPLY", pendingIntent).addRemoteInput(remoteInput).build()


        val notification =
            NotificationCompat.Builder(this@MainActivity, channelId).setContentTitle("Demo Title")
                .setContentText("Demo content").setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentIntent(pendingIntent).addAction(detailsActivityAction)
                .addAction(settingsActivityAction).addAction(replyAction)
                .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_HIGH).build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}