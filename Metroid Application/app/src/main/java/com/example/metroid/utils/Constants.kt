package com.example.metroid.utils

import java.util.regex.Pattern

object Constants {
   // you can put your ip here
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

    val METRO_LINES = arrayListOf("First Line","Second Line" ,"Third Line")

    val classList = arrayListOf("A", "B", "C")


     val FIRST_LINE = arrayListOf(
        "",
        "New El-Marg",
        "El-Marg",
        "Ezbet El-Nakhl",
        "Ain Shams",
        "El-Matareyya",
        "Helmeyet El-Zeitoun",
        "Hadayeq El-Zeitoun",
        "Saray El-Qobba",
        "Hammamat El-Qobba",
        "Kobry El-Qoba",
        "Mansheyet El-Sadr",
        "El-Demerdash",
        "Ghamra",
        "Al-Shohadaa",
        "Orabi",
        "Gamal Abdel-Nasser",
        "El-Sadat",
        "Saad Zaghloul",
        "El-Sayeda Zainab",
        "El-Malek El-Saleh",
        "Mar Girgis",
        "El-Zahraa",
        "Dar El-Salam",
        "Hadayeq El-Maadi",
        "Maadi",
        "Sakanat El-Maadi",
        "Tora El-Balad",
        "Kozzika",
        "Tora El-Asmant",
        "El-Maasara",
        "Hadayeq Helwan",
        "Wadi Hof",
        "Helwan University",
        "Ain Helwan",
        "Helwan"
    )
     val SECOND_LINE = arrayListOf(
        "",
        "Shubra El-Kheima",
        "Kolleyet El-Zeraa",
        "El-Mazallat",
        "El-Khalafawy",
        "St. Teresa",
        "Rod El-Farag",
        "Massara",
        "Al-Shodaa",
        "Attaba",
        "Mohamed Naguib",
        "El-Sadat",
        "Opera",
        "Dokki",
        "El-Bohooth",
        "Cairo University",
        "Faisal",
        "Giza",
        "Om El-Masryeen",
        "Sakyat Mekki",
        "El-Mounib"
    )


     val THIRD_LINE = arrayListOf(
        "",
        "Attaba",
        "Bab El-Shaarya",
        "El-Geish",
        "Abdou Basha",
        "Abbasseya",
        "Cairo Fairgrounds",
        "Cairo Stadium",
        "Kolleyet El-Banat",
        "El-Ahram",
        "Haron",
        "Heliopolis",
        "Alf Maskan",
        "Nady El-Shams",
        "El-Nozha",
        "Hesham Barakat",
        "Qobaa",
        "Omar Ibn El-Khattab",
        "El-Haykestep",
        "Adly Mansour"
    )


    fun getFirstLine(): HashMap<String, String> {
        val firstLine = HashMap<String, String>()

        for (i in 1..35) {
            firstLine.put("a$i", FIRST_LINE[i])
        }

        return firstLine
    }

    fun getSecondLine(): HashMap<String, String> {
        val secondLine = HashMap<String, String>()

        for (i in 1..20) {
            secondLine.put("b$i", SECOND_LINE[i])
        }

        return secondLine
    }

    fun getThirdLine(): HashMap<String, String> {
        val thirdLine = HashMap<String, String>()

        for (i in 1..19) {
            thirdLine.put("c$i", THIRD_LINE[i])
        }

        return thirdLine
    }



}