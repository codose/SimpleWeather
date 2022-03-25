package com.android.weathersimple.data.api

import com.android.weathersimple.data.model.all.WeatherResponseSchema
import com.android.weathersimple.data.model.details.WeatherDetailsResponseSchema
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("group")
    suspend fun getWeatherData(
        @Query("id") groupIds: String,
        @Query("units") units: String,
        @Query("appId") appId: String
    ): WeatherResponseSchema

    @GET("forecast")
    suspend fun getWeatherDetails(
        @Query("id") id: Int,
        @Query("appId") appId: String,
        @Query("units") units: String
    ): WeatherDetailsResponseSchema
}
