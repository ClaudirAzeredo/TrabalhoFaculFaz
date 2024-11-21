package com.example.trabalhofaculfaz

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
        override fun onMessageReceived(remoteMessage: RemoteMessage) {
            // Lógica para lidar com notificações push recebidas
        }

        override fun onNewToken(token: String) {
            // Salve o novo token para notificações push
        }
    }