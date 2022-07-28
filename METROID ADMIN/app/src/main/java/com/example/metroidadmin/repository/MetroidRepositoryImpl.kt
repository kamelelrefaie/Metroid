package com.example.metroidadmin.repository

import com.example.metroidadmin.remote.MetroApi
import com.example.metroidadmin.remote.responses.FeedbacRespone
import javax.inject.Inject

class MetroidRepositoryImpl @Inject constructor(
    private val metroApi: MetroApi
) : MetroidRepository {
    override suspend fun getFeedback(): FeedbacRespone {
        return metroApi.getFeedback()
    }


}