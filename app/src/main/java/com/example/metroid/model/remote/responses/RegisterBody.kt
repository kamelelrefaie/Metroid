package com.example.metroid.model.remote.responses

data class RegisterBody (
     val firstName: String,
     val lastName: String,
     val email: String,
     val password: String
)