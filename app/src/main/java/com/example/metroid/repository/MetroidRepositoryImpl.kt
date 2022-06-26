package com.example.metroid.repository

import com.example.metroid.model.remote.MetroidApi
import com.example.metroid.model.remote.responses.*
import java.time.LocalDate
import javax.inject.Inject

class MetroidRepositoryImpl @Inject constructor(
    private val metroidApi: MetroidApi
) : MetroidRepository {
    override suspend fun login(email: String, password: String): Login {
        return metroidApi.login(email, password)
    }

    override suspend fun register(body: RegisterBody): Login {
        return metroidApi.register(body)
    }

    override suspend fun requestPassword(email: String): Login {
        return metroidApi.requestPassword(email)
    }

    override suspend fun getStationId(from: String, to: String): StationResponse {
        return metroidApi.getStationId(from, to)
    }

    override suspend fun getTripTime(source: Int, dest: Int): TripResponse {
        return metroidApi.getTripTime(source, dest)
    }

    override suspend fun getTripAtSpecificTime(
        source: Int,
        dest: Int,
        arr: LocalDate
    ): TripResponse {
        return metroidApi.getTripAtSpecificTime(source, dest, arr)
    }

    override suspend fun confirmTicketRequest(
        userId: Long,
        tripId: Long,
        ticketModel: TicketModel
    ): Login {
        return metroidApi.confirmTicketRequest(userId, tripId, ticketModel)
    }

    override suspend fun getTicketInfo(userId: Long): TicketInfoData {
        return metroidApi.getTicketInfo(userId)
    }

    override suspend fun deleteTicket(userId: Long): Login {
        return metroidApi.deleteTicket(userId)
    }

    override suspend fun submitFeedback(feedbackReq: FeedBackRequest): Login {
        return metroidApi.submitFeedback(feedbackReq)
    }

    override suspend fun postTrip(metroTripRequest: MetroTripRequest): Login {
        return metroidApi.postTrip(metroTripRequest)
    }

    override suspend fun getTripCreditAndTrips(userId: Long): MetroCreditTripResponse {
        return metroidApi.getTripCreditAndTrips(userId)
    }

    override suspend fun updateProfile(id: Long, updateProfileData: UpdateProfileData): Login {
        return metroidApi.updateProfile(id, updateProfileData)
    }

    override suspend fun getMetroTrips(userId: Long): GetMetroTripsHistoryFromApi {
        return metroidApi.getMetroTrips(userId)
    }

    override suspend fun fetchNameAndId(email: String): NameIdRequest {
        return metroidApi.fetchNameAndId(email)
    }


}