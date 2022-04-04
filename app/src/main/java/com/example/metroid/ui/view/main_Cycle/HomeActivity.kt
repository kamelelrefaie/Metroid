package com.example.metroid.ui.view.main_Cycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metroid.R
import com.example.metroid.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var activityHomeBinding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)



    }
}