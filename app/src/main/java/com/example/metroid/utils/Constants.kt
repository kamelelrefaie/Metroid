package com.example.metroid.utils

import java.util.regex.Pattern

object Constants {

    val RESERVATION_LIST = listOf<String>(
        "Maadi Station",
        "Ramsis Station",
        "Ramsis Station",
        "Ramsis Station",
        "Ramsis Station",
        "Ramsis Station"
    )

    const val BASE_URL = "http://192.168.1.61:8080/"

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun checkEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    val trainStationNameList = arrayListOf(
        "Masr", "ElRaml", "ElAiat", "Siedy Gaber", "ElMansouria", "Aswan",
        "Oksor", "Kena", "Monofia", "Sers", "Matrouh", "Sharkia", "Embaba", "Asiut", "Souhag",
        "Halayeb", "Shalateen", "Taba", "Kafr ElShiekh", "Kafr ElDawar"
    )

    val classList = arrayListOf("A", "B", "C")
}