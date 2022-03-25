package com.android.weathersimple.domain.model

data class WeatherDomain(
    val id: Int,
    val countryCode: String,
    val city: String,
    val temp: Double,
    val weatherName: String,
    val weatherImage: String,
    val dateTime: Long,
    val isFavourite: Boolean = false
)
