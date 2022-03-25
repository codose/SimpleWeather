package com.android.weathersimple.data.repository

import com.android.weathersimple.domain.model.details.WeatherDetailsDomain
import com.android.weathersimple.domain.model.WeatherDomain
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherDataFromAPi(): List<WeatherDomain>
    suspend fun getWeatherDetailsFromAPi(id: Int): Map<String, List<WeatherDetailsDomain>>
    suspend fun insertWeatherToDb(items: List<WeatherDomain>)
    suspend fun getWeatherFromDb(): Flow<List<WeatherDomain>>
    suspend fun addCityToFavorite(id: Int, isFav: Boolean)
    suspend fun searchCity(search: String): Flow<List<WeatherDomain >>
}
