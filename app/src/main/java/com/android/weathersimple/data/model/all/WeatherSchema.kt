package com.android.weathersimple.data.model.all

import com.google.gson.annotations.SerializedName

data class WeatherSchema(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("weather")
    val weather: List<WeatherDetailsSchema>
)
