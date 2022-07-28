package com.example.metroidadmin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.metroidadmin.R
import com.example.metroidadmin.databinding.ActivityShowFeedbackBinding
import com.example.metroidadmin.remote.responses.FeedbacResponeItem
import com.example.metroidadmin.ui.adapter.FeedbackAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ShowFeedback : AppCompatActivity() {
    private lateinit var activityShowFeedbackBinding: ActivityShowFeedbackBinding
    private var fbList = ArrayList<FeedbacResponeItem>()
    private lateinit var viewModel: AdminViewModel
    private lateinit var adapter: FeedbackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowFeedbackBinding = ActivityShowFeedbackBinding.inflate(layoutInflater)
        setContentView(activityShowFeedbackBinding.root)
        fbList = ArrayList()
        viewModel = ViewModelProvider(this)[AdminViewModel::class.java]

        viewModel.getFeedback()

        activityShowFeedbackBinding.rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.feedBackList.observe(this) {
            fbList = it as ArrayList<FeedbacResponeItem>
            adapter.setList(it)
        }
        adapter = FeedbackAdapter()

        activityShowFeedbackBinding.rv.adapter = adapter


    }
}