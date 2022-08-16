package com.example.heroapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class BiographyModel(
    @SerializedName("full-name") var fullName: String,
    @SerializedName("alter-egos") var alteregos: String,
    @SerializedName("aliases") var aliases: ArrayList<String>,
    @SerializedName("place-of-birth") var placeOfBirth: String,
    @SerializedName("first-appearance") var firstAppearance: String,
    @SerializedName("publisher") var publisher: String,
    @SerializedName("alignment") var alignment: String
) : Parcelable
