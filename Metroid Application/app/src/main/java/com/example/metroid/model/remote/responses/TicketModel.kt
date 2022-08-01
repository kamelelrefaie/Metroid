package com.example.metroid.model.remote.responses


data class TicketModel(
    private val tClass: String,
    private val price: Int,
    private val seats: List<SeatModel>,
    private val quantity: Int
)