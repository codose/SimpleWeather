package com.android.weathersimple.ui.model

data class WeatherDetailsItem(
    val id: Int,
    val countryCode: String,
    val city: String,
    val temp: String,
    val weatherName: String,
    val weatherImage: String,
    val date: String,
    val time: String
)
