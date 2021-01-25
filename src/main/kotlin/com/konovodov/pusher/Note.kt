package com.konovodov.pusher


data class Note(
    val id: Long = 1,
    val author: String = "",
    val avatar: String = "",
    val headerText: String = "",
    val published: Long = 0,
    val content: String = "",
    val link: String = "",
    val videoLink: String = "",
    val likes: Int = 0,
    val likedByMe: Boolean = false,
    val shared: Int = 0,
    val watched: Int = 0
)
