package com.example.metroid.ui.view.viewmodel.main_cycle

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metroid.model.local.UserData
import com.example.metroid.model.remote.responses.GetMetroTripsHistoryFromApiItem
import com.example.metroid.model.remote.responses.MetroTripRequest
import com.example.metroid.model.remote.responses.UpdateProfileData
import com.example.metroid.repository.DataStoreManager
import com.example.metroid.repository.MetroidRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetroViewModel @Inject constructor(
    private val repository: MetroidRepository,
    private val dataStore: DataStoreManager
) :
    ViewModel() {

    val credits = MutableLiveData<String>()
    val trips = MutableLiveData<String>()

    suspend fun getPrefData(context: Context): UserData {
        val userDataFlow: Flow<UserData> = dataStore.getFromDataStore()
        val userData = userDataFlow.first()

        return userData

    }

    fun postTrip(price: Int, from: String, to: String, context: Context) {

        viewModelScope.launch {
            val id = getPrefData(context).id
            val metroTripRequest = MetroTripRequest(price, from, to, id)
            try {
                repository.postTrip(metroTripRequest)
            } catch (e: Exception) {
                Log.e("viewmodel", "$e")
            }
        }

    }

    fun getTripCreditsAndCount(context: Context) {
        viewModelScope.launch {
            try {
                val id = getPrefData(context).id
                val response = repository.getTripCreditAndTrips(id)
                credits.value = response.credit
                trips.value = response.trips
            } catch (e: Exception) {
                Log.e("viewmodel", "$e")

            }
        }

    }

    fun editProfileFragment(updateProfileData: UpdateProfileData, context: Context) {
        viewModelScope.launch {
            try {
                val id = getPrefData(context).id
                val response = repository.updateProfile(id, updateProfileData)
                Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("viewmodel", "$e")

            }
        }

    }

    suspend fun getMetroTrips(context: Context): ArrayList<GetMetroTripsHistoryFromApiItem> {
        var response = ArrayList<GetMetroTripsHistoryFromApiItem>()

            try {
                val id = getPrefData(context).id
                response = repository.getMetroTrips(id)
                Log.e("viewmodel", " $response")

            } catch (e: Exception) {
                Log.e("viewmodel", "$e $response")
            }

        return response
    }


}