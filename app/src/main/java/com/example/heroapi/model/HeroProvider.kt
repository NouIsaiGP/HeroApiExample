package com.example.heroapi.model

import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


class HeroProvider {
    companion object {

        var heros:MutableList<HeroModel> = mutableListOf()
    }
}