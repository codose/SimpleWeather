package com.android.weathersimple.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_city")
data class WeatherCityEntity(
    @PrimaryKey
    val id: Int,
    val countryCode: String,
    val city: String,
    val temp: Double,
    val weatherName: String,
    val weatherImage: String,
    val isFavourite: Boolean,
    val dateTime: Long
)
