package com.leandro1995.seito.model.design

import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.leandro1995.seito.R

class Notification(
    private val context: Context,
    private val title: String,
    private val content: String,
    private val activity: Activity
) {

    @SuppressLint("MissingPermission")
    fun showNotification() {
        createChannel()
        with(NotificationManagerCompat.from(context)) {
            notify(idRandom(), notificationCompatBuild())
        }
    }

    private fun notificationCompatBuild() =
        NotificationCompat.Builder(context, getText(idString = R.string.id_channel)).setSmallIcon(0)
            .setContentTitle(title).setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent())
            .build()

    private fun pendingIntent() = PendingIntent.getActivity(
        context, 0, Intent(context, activity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }, PendingIntent.FLAG_IMMUTABLE
    )

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
                createNotificationChannel(
                    NotificationChannel(
                        getText(idString = R.string.id_channel),
                        getText(idString = R.string.name_channel),
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                )
            }
        }
    }

    private fun idRandom() = (1..300).random()

    private fun getText(@StringRes idString: Int): String = context.getText(idString).toString()
}