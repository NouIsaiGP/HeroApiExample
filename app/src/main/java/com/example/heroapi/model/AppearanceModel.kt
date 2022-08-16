package com.example.heroapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class AppearanceModel(
    @SerializedName("gender") var gender: String,
    @SerializedName("race") var race: String,
    @SerializedName("height") var height: ArrayList<String>,
    @SerializedName("weight") var weight: ArrayList<String>,
    @SerializedName("eye-color") var eyeColor: String,
    @SerializedName("hair-color") var hairColor: String
) : Parcelable
