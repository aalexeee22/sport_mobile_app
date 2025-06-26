package com.example.sport_app.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.sport_app.data.models.ExerciseResponse


interface SportApi {

    @GET("v2/exerciseinfo/")
    fun getExercises(
        @Query("language") language: Int = 2, // English
        @Query("limit") limit: Int = 10
    ): Call<ExerciseResponse>

}
