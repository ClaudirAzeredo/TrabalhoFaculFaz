package com.example.trabalhofaculfaz

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
        override fun onMessageReceived(remoteMessage: RemoteMessage) {
        }

        override fun onNewToken(token: String) {
        }
    }
