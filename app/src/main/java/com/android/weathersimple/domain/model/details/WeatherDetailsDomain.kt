package com.android.weathersimple.domain.model.details

data class WeatherDetailsDomain(
    val city: String,
    val temperature: Double,
    val weather: String,
    val time: String,
    val date: String,
    val weatherImage: String
)
