package com.example.metroidadmin.repository

import com.example.metroidadmin.remote.responses.FeedbacRespone

interface MetroidRepository {
    suspend fun getFeedback(): FeedbacRespone
}