package com.example.heroapi.data.network

import com.example.heroapi.core.RetrofitHelper
import com.example.heroapi.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeroService {

    //llamada a el objeto para  consumir la API
    private val retrofit = RetrofitHelper.getRetrofit()

    //Funcion para llamar y obtener un heroe de la API
    suspend fun getHero(id: String): HeroModel {

        return withContext(Dispatchers.IO) {
            val call = retrofit.create(HeroApi::class.java).getHero("$id/")
            call
        }

    }

    //Funcion para llamar y obtener un Heroe de la Api desde una response global
    suspend fun getHero2(id: String): ResponseApi {

        return withContext(Dispatchers.IO) {
            val call = retrofit.create(HeroApi::class.java).getHero2("$id/")
            call
        }
    }

    //Funcion para obtener un heroe por nombre
    suspend fun getHeroByName(name: String): ResponseApi {
        return withContext(Dispatchers.IO) {
            val call = retrofit.create(HeroApi::class.java).getHeroByName("$name")
            call


            /*val hero = HeroModel("error", "", "", ImageModel(""), PowerStatsModel("", "", "", "", "", ""), WorkModel("", ""), ConnectionsModel("", ""),)
            val call = retrofit.create(HeroApi::class.java).getHeroByName("$name")
            if(call.response.equals("error")){
                hero
            }else{
                call.results.first().response = "success"
                call.results.first()
            }*/
        }
    }
}