package com.example.sport_app.network

import com.example.sport_app.data.models.MathRequest
import com.example.sport_app.data.models.MathResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BmiApi {
    @POST("v4/")
    fun calculateBmi(@Body expression: MathRequest): Call<MathResponse>
}
