package com.example.heroapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class ConnectionsModel(
    @SerializedName("group-affiliation") var group: String,
    @SerializedName("relatives") var relatives: String) : Parcelable
