package com.konovodov.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.google.gson.Gson
import java.io.FileInputStream
import java.util.*


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    while (true) {
        println(
            """Select Push type to send:
                    1. Like Data Push
                    2. New Note Data Push
                    3. Notification Push
                    4. Unknown Data Push"""
        )
        val line = readLine()
        when (line) {
            "1" -> sendLikePush()
            "2" -> sendNewNotePush()
            "3" -> sendNotificationPush()
            "4" -> sendUnknownDataPush()
            else -> continue
        }
    }
}

fun sendLikePush() {
    val message = Message.builder()
        .putData("action", "LIKE")
        .putData(
            "content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}

fun sendNewNotePush() {
    val note = Note(
        id = 0,
        author = "Лимпопоша",
        content = "На самом деле самого дела нет. В самой деятельности заключена самость дела — и наоборот."
    )

    val gson = Gson()

    val str = gson.toJson(note)

    val message = Message.builder()
        .putData("action", "NEW_NOTE")
        .putData(
            "content", str.trimIndent()
        )
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}

fun sendUnknownDataPush() {
    val message = Message.builder()
        .putData("action", "DISLIKE")
        .putData(
            "content", """{
          "userId": 2,
          "userName": "Fedot",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}

fun sendNotificationPush() {
    val notification = Notification.builder()
        .setTitle("Notification Title!")
        .setBody("Text!")
        .build()

    val message = Message.builder()
        .setNotification(notification)
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}
