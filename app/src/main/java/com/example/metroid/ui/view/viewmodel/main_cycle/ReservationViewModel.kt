package com.example.metroid.ui.view.viewmodel.main_cycle

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.metroid.model.local.UserData
import com.example.metroid.model.remote.responses.*
import com.example.metroid.repository.DataStoreManager
import com.example.metroid.repository.MetroidRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.http.Query
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val repository: MetroidRepository,
    private val dataStore: DataStoreManager
) :
    ViewModel() {

    suspend fun getStationId(from: String, to: String): StationResponse {
        return repository.getStationId(from, to)
    }

    suspend fun getTrainTime(source: Int, dest: Int): TripResponse {
        return repository.getTripTime(source, dest)
    }

    suspend fun getTrainAtSpecificTime(source: Int, dest: Int, arr: LocalDate): TripResponse {
        return repository.getTripAtSpecificTime(source, dest, arr)
    }

    suspend fun getPrefData(context: Context): UserData {
        val userDataFlow: Flow<UserData> = dataStore.getFromDataStore()
        val userData = userDataFlow.first()

        return userData

    }

    suspend fun confirmTicketRequest(userId: Long, tripId: Long, ticketModel: TicketModel): String {
        val reqBody = repository.confirmTicketRequest(userId, tripId, ticketModel)
        return reqBody.message
    }

    suspend fun getTicketInfo(
        userId: Long
    ): TicketInfoData {
        return repository.getTicketInfo(userId)
    }

    suspend fun deleteTicket(
        userId: Long
    ): Login {
        return repository.deleteTicket(userId)
    }
}