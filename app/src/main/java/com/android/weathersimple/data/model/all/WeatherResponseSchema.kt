package com.android.weathersimple.data.model.all

import com.google.gson.annotations.SerializedName

data class WeatherResponseSchema(
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<WeatherSchema>
)
