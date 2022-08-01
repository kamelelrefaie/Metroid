package com.example.metroid.model.remote.responses

data class FeedBackRequest(
    val subject: String,
    val content: String,
    val rate: Float,
    val userId:Long,
)
