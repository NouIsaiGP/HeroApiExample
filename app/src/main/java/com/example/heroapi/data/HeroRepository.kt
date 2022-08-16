package com.example.heroapi.data

import android.util.Log
import com.example.heroapi.model.HeroModel
import com.example.heroapi.model.HeroProvider
import com.example.heroapi.data.network.HeroService
import com.example.heroapi.model.ResponseApi

class HeroRepository {

    private val api = HeroService()
    private var herolist = arrayListOf<HeroModel>()


    suspend fun initList(): ArrayList<HeroModel> {
        for (i in 1..730) {
            val hero = api.getHero(i.toString())
            Log.e("Datos: ", hero.toString())
            if (hero.response == "success") {
                herolist.add(hero)

            }
        }
        HeroProvider.heros = herolist
        Log.e("Ayudadaadada", herolist.toString())
        return herolist
    }

    /*Funciones que ocupan corrutinas para llamar la API de superhero*/
    suspend fun heroById(id: String): HeroModel {
        return api.getHero(id)
    }

    suspend fun heroById2(id: String): ResponseApi {
        return api.getHero2(id)
    }

    suspend fun heroByName(name: String): ResponseApi {
        val output = name.replace(" ", "%20")
        return api.getHeroByName(output)
    }
}