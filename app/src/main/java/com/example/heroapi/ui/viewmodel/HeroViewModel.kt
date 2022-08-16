package com.example.heroapi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heroapi.data.HeroRepository
import com.example.heroapi.model.HeroModel
import com.example.heroapi.model.ResponseApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HeroViewModel() : ViewModel() {

    lateinit var recyclerListLiveData: MutableLiveData<ResponseApi>
    lateinit var repository: HeroRepository
    lateinit var response: ResponseApi
    lateinit var heroLiveData: MutableLiveData<HeroModel>

    init {
        recyclerListLiveData = MutableLiveData()
        heroLiveData = MutableLiveData()
        repository = HeroRepository()
        response = ResponseApi("", "", ArrayList<HeroModel>())
    }

    fun getRecyclerListObserver(): MutableLiveData<ResponseApi> {
        return recyclerListLiveData
    }

    fun addFiveMore() {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in response.results.size + 1..response.results.size + 5) {
                val hero = repository.heroById(i.toString())
                Log.e("Datos: ", hero.toString())
                if (hero.response == "success") {
                    if (response.results.contains(hero)) {
                        response.results.remove(hero)
                    } else {
                        response.results.add(hero)
                        recyclerListLiveData.postValue(response)
                    }
                }
            }
        }
    }

    fun findHeroByName(name: String) {
        response.results.clear()
        recyclerListLiveData.postValue(response)

        viewModelScope.launch(Dispatchers.IO) {
            val responseApi = repository.heroByName(name)
            for (hero in responseApi.results){
                hero.response = "success"
                Log.e("ITEM " , hero.toString())
                response.results.add(hero)
                recyclerListLiveData.postValue(response)
            }

            //response.results = responseApi.results



            /*val hero = repository.heroByName(name)
            when(hero){
                is HeroModel -> {
                    Log.e("bien", "bien")
                    heroLiveData.postValue(hero)
                    Log.e("heroe", heroLiveData.value.toString())

                }else -> {
                Log.e("mal", "mal")
            }
            }*/
        }
    }

    fun addOneMore(total: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = total + 2
            val hero = repository.heroById(id.toString())
            Log.e("Datos: ", hero.toString())
            if (hero.response == "success") {
                response.results.add(hero)
                recyclerListLiveData.postValue(response)

            }

        }
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in 1..10) {
                val hero = repository.heroById(i.toString())
                Log.e("Datos: ", hero.toString())
                if (hero.response == "success") {
                    response.results.add(hero)
                    recyclerListLiveData.postValue(response)
                }
            }
        }
    }


}