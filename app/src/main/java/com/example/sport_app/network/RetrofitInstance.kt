package com.example.sport_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val exerciseApi: SportApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://wger.de/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SportApi::class.java)
    }
    val mathApi: BmiApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.mathjs.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BmiApi::class.java)
    }


}
