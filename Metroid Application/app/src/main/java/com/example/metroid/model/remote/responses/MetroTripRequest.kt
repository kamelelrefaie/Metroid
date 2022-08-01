package com.example.metroid.model.remote.responses

data class MetroTripRequest(
    private val price: Int,
    private val from: String,
    private val to: String,
    private val appUserId: Long
)