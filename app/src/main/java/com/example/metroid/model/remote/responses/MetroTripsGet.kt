package com.example.metroid.model.remote.responses

import java.time.LocalDate

data class MetroTripsGet(
    val price: Int,
    val fromStation: String,
    val toStationval: String,
    val date: LocalDate
)