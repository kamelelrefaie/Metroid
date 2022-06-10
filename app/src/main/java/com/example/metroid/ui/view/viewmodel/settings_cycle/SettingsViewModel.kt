package com.example.metroid.ui.view.viewmodel.settings_cycle

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.metroid.model.local.UserData
import com.example.metroid.model.remote.responses.FeedBackRequest
import com.example.metroid.model.remote.responses.Login
import com.example.metroid.repository.DataStoreManager
import com.example.metroid.repository.MetroidRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel

class SettingsViewModel @Inject constructor(
    private val repository: MetroidRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {

    val message = MutableLiveData<Login>()
    suspend fun getPrefData(context: Context): UserData {
        val userDataFlow: Flow<UserData> = dataStore.getFromDataStore()
        val userData = userDataFlow.first()

        return userData
    }

    suspend fun submitFeedback(feedBackRequest: FeedBackRequest) {
        message.value = repository.submitFeedback(feedBackRequest)
    }

}