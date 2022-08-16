package com.example.heroapi.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//objeto para uso de la api
object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/5470660616305063/")

            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }
}