package com.example.metroid.model.remote.responses

data class GetMetroTripsHistoryFromApiItem(
    val date: String,
    val fromStation: String,
    val price: Int,
    val toStation: String
)