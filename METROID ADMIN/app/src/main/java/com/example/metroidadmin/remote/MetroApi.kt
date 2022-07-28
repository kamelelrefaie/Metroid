package com.example.metroidadmin.remote

import com.example.metroidadmin.remote.responses.FeedbacRespone
import com.example.metroidadmin.remote.responses.Login
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MetroApi {
    @POST("/login")
    suspend fun login(@Query("username") email: String, @Query("password") password: String): Login

    @GET("/api/v1/home/feedback")
    suspend fun getFeedback(
    ): FeedbacRespone
}