package com.example.heroapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class PowerStatsModel(
    @SerializedName("intelligence") var intelligence: String,
    @SerializedName("strength") var strength: String,
    @SerializedName("speed") var speed: String,
    @SerializedName("durability") var durability: String,
    @SerializedName("power") var power: String,
    @SerializedName("combat") var combat: String
) : Parcelable
