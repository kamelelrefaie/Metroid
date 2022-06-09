package com.example.metroid.model.remote.responses

data class TicketInfoData(
    val arrTime: String,
    val confirmDate: String,
    val deptTime: String,
    val firstName: String,
    val message: Int,
    val nameArr: String,
    val nameDept: String,
    val price: Int,
    val seats: List<Int>,
    val stateArr: String,
    val stateDept: String,
    val tripId: Int
)