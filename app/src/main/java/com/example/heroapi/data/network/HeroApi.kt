package com.example.heroapi.data.network

import com.example.heroapi.model.HeroModel
import com.example.heroapi.model.ResponseApi
import retrofit2.http.GET
import retrofit2.http.Path
//Metodos para obtener el heroe por id o nombre
interface HeroApi {
    @GET("{id}")
    suspend fun getHero(@Path("id") id: String): HeroModel

    @GET("{id}")
    suspend fun getHero2(@Path("id") id: String): ResponseApi

    @GET("search/{name}")
    suspend fun getHeroByName(@Path("name") name: String): ResponseApi
}