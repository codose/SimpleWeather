package com.android.weathersimple.data.model.details

import com.google.gson.annotations.SerializedName

data class WeatherDetailsResponseSchema(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<WeatherSchema>,
    @SerializedName("message")
    val message: Int
)
