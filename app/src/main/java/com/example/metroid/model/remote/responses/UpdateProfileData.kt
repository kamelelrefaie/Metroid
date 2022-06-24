package com.example.metroid.model.remote.responses

data class UpdateProfileData(
    val username: String,
    val email: String,
    val phone: Int,
    val password: String
)
