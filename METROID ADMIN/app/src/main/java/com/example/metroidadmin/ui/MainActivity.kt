package com.example.metroidadmin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metroidadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.cvFeedback.setOnClickListener {
            startActivity(Intent(this, ShowFeedback::class.java))

        }



        activityMainBinding.cvAddStation.setOnClickListener {
            startActivity(Intent(this, AddStation::class.java))
        }

        activityMainBinding.cvTrip.setOnClickListener {
            startActivity(Intent(this, AddTrip::class.java))

        }

    }
}