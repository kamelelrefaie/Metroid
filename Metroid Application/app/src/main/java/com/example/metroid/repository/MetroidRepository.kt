package com.example.metroid.repository

import com.example.metroid.model.remote.responses.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface MetroidRepository {
    suspend fun login(email: String, password: String): Login
    suspend fun register(body: RegisterBody): Login
    suspend fun requestPassword(email: String): Login

    suspend fun getStationId(from: String, to: String): StationResponse
    suspend fun getTripTime(source: Int, dest: Int): TripResponse
    suspend fun getTripAtSpecificTime(source: Int, dest: Int, arr: LocalDate): TripResponse
    suspend fun confirmTicketRequest(userId: Long, tripId: Long, ticketModel: TicketModel): Login
    suspend fun getTicketInfo(userId: Long): TicketInfoData
    suspend fun deleteTicket(userId: Long): Login
    suspend fun submitFeedback(feedbackReq: FeedBackRequest): Login
    suspend fun postTrip(metroTripRequest: MetroTripRequest): Login
    suspend fun getTripCreditAndTrips(userId: Long): MetroCreditTripResponse
    suspend fun updateProfile(id: Long, updateProfileData: UpdateProfileData): Login
    suspend fun getMetroTrips(userId: Long): GetMetroTripsHistoryFromApi
    suspend fun fetchNameAndId(email: String): NameIdRequest
}