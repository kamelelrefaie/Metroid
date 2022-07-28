package com.example.metroidadmin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metroidadmin.R
import dagger.hilt.android.AndroidEntryPoint


class AddStation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_station)
    }
}