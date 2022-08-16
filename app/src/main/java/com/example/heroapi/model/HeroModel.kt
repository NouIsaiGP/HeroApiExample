package com.example.heroapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
data class HeroModel(
    @SerializedName("response") var response: String? = null,
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("image") var image: ImageModel,
    @SerializedName("powerstats") var powerstats: PowerStatsModel,
    @SerializedName("biography") var biography: BiographyModel,
    @SerializedName("appearance") var appearance: AppearanceModel,
    @SerializedName("work") var work: WorkModel,
    @SerializedName("connections") var connections: ConnectionsModel,
) : Parcelable {

}