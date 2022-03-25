package com.android.weathersimple.data.model.all

import com.google.gson.annotations.SerializedName

data class WeatherDetailsSchema(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)
