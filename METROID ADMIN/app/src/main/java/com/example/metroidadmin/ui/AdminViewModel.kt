package com.example.metroidadmin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metroidadmin.remote.responses.FeedbacResponeItem
import com.example.metroidadmin.repository.MetroidRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AdminViewModel @Inject constructor(
    private val repository: MetroidRepository,
) : ViewModel() {
    var feedBackList = MutableLiveData<List<FeedbacResponeItem>>()


     fun getFeedback() {
       viewModelScope.launch {
           feedBackList.value = repository.getFeedback()
           Log.e("this", repository.getFeedback().toString())
       }
    }

}