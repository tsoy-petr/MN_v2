package com.android.hootr.dogs.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.hootr.dogs.R
import com.android.hootr.dogs.view.MainActivity

class NotificationsHelper(val context: Context) {

    private val CHANNALE_ID = "Dog channale id"
    private val NOTIFICATION_ID = 123

    fun createNotification() {

        createNotificationChannel()

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.dog)

        val notifiction = NotificationCompat.Builder(context, CHANNALE_ID)
            .setSmallIcon(R.drawable.dog_icon)
            .setLargeIcon(icon)
            .setContentTitle("Dog retrieved")
            .setContentText("This notification has some content")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(icon)
                    .bigLargeIcon(null)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notifiction)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNALE_ID
            val decriptionText = "Channel decriptions"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNALE_ID, name, importance).apply {
                description = decriptionText
            }

            val notifactionManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifactionManager.createNotificationChannel(channel)
        }
    }
}