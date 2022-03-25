package com.android.weathersimple.data.source.remote

import com.android.weathersimple.data.model.all.WeatherResponseSchema
import com.android.weathersimple.data.model.details.WeatherDetailsResponseSchema

interface RemoteDataSource {
    suspend fun getAllWeatherData(): WeatherResponseSchema
    suspend fun getWeatherDetails(id: Int): WeatherDetailsResponseSchema
}
