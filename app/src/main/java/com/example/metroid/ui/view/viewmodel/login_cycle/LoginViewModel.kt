package com.example.metroid.ui.view.viewmodel.login_cycle

import android.content.Context
import android.util.Log

import androidx.lifecycle.ViewModel
import com.example.metroid.model.local.UserData
import com.example.metroid.model.remote.responses.NameIdRequest
import com.example.metroid.model.remote.responses.RegisterBody
import com.example.metroid.repository.DataStoreManager

import com.example.metroid.repository.MetroidRepository
import com.example.metroid.utils.Constants.checkEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.jar.Attributes
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MetroidRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {


    suspend fun login(email: String, password: String): Boolean {
        if (!checkEmail(email)) {
            return false;
        }
        val loginV = repository.login(email, password)
        Log.e("here", "$email  $password")

        return loginV.message.equals("success")
    }

    suspend fun register(body: RegisterBody): Boolean {
        var registerV = repository.register(body)
        return registerV.message.equals("success")
    }

    suspend fun requestPassword(email: String): Boolean {
        var registerV = repository.requestPassword(email)
        return registerV.message.equals("success")
    }

    suspend fun saveNameAndUserIdToDataStore(name: String, userId: Long, context: Context) {
        dataStore.save(UserData(name, userId))
    }
    suspend fun getNameId(email:String): NameIdRequest {
       return repository.fetchNameAndId(email)
    }
}