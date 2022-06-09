package com.example.metroid.ui.view.main_Cycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.metroid.R
import com.example.metroid.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var activityHomeBinding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        val navController = findNavController(R.id.fragmentHomeHost)
        activityHomeBinding.bottomNavigationView.setupWithNavController(navController)


    }

    fun hideBottomNavigationView() {
        activityHomeBinding.bottomNavigationView.clearAnimation()
        activityHomeBinding.bottomNavigationView.animate().translationY(activityHomeBinding.bottomNavigationView.height.toFloat()).duration = 300
        activityHomeBinding.bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNavigationView() {
        activityHomeBinding.bottomNavigationView.clearAnimation()
        activityHomeBinding.bottomNavigationView.animate().translationY(0f).duration = 300
        activityHomeBinding.bottomNavigationView.visibility = View.VISIBLE

    }
}