package com.example.metroid.model.remote

import com.example.metroid.model.remote.responses.*
import retrofit2.http.*
import java.time.LocalDate

interface MetroidApi {
    @POST("/login")
    suspend fun login(@Query("username") email: String, @Query("password") password: String): Login

    @POST("/api/v1/registration")
    suspend fun register(@Body body: RegisterBody): Login

    @POST("/api/v1/registration/forget")
    suspend fun requestPassword(@Query("email") email: String): Login

    @GET("/api/v1/home/station")
    suspend fun getStationId(@Query("from") from: String, @Query("to") to: String): StationResponse

    @GET("/api/v1/home/trip")
    suspend fun getTripTime(@Query("source") source: Int, @Query("dest") dest: Int): TripResponse

    @GET("/api/v1/home/trip/date")
    suspend fun getTripAtSpecificTime(
        @Query("source") source: Int, @Query("dest") dest: Int,
        @Query("arr") arr: LocalDate
    ): TripResponse

    @POST("/api/v1/home/ticket/confirm")
    suspend fun confirmTicketRequest(
        @Query("appUser") userId: Long, @Query("tripId") tripId: Long,
        @Body ticketModel: TicketModel
    ): Login

    @GET("/api/v1/home/ticket/getTicketData")
    suspend fun getTicketInfo(
        @Query("id") userId: Long
    ): TicketInfoData

    @DELETE("/api/v1/home/ticket/delete")
    suspend fun deleteTicket(
        @Query("id") userId: Long
    ): Login

    @POST("/api/v1/home/feedback")
    suspend fun submitFeedback(
        @Body feedbackReq: FeedBackRequest
    ): Login
}