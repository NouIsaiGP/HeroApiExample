package com.example.heroapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class ResponseApi(
    @SerializedName("response")
    var response: String ? = null,
    @SerializedName("results-for")
    var results_for: String? = null,
    @SerializedName("results")
    var results: ArrayList<HeroModel> = arrayListOf()
) : Parcelable