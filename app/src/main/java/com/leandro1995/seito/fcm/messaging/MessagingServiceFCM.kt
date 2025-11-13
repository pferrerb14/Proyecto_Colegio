package com.leandro1995.seito.fcm.messaging

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.leandro1995.seito.activity.HomeStudentActivity
import com.leandro1995.seito.model.design.Notification

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MessagingServiceFCM : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Handler(Looper.getMainLooper()).post {
            message.notification?.let {
                Notification(
                    context = this,
                    title = it.title.orEmpty(),
                    content = it.body.orEmpty(),
                    activity = HomeStudentActivity()
                ).showNotification()
            }
        }
    }
}