package com.example.heroapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class WorkModel(
    var occupation: String,
    var base: String
) : Parcelable
