package com.sk.notification

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat

class SecondActivity : AppCompatActivity() {
    private val KEY_REPLY = "key_reply"
    private val channelId = "com.sk.notification.channelfirst"
    private val notificationId = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        receiveInput()
    }

    private fun receiveInput() {
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            val inputStr = remoteInput.getCharSequence(KEY_REPLY).toString()
            findViewById<TextView>(R.id.name_tv).text = inputStr
            val repliedNotification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info).setContentText("Your reply received")
                .build()
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)
        }
    }
}