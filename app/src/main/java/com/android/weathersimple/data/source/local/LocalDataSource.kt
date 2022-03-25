package com.android.weathersimple.data.source.local

import com.android.weathersimple.data.local.entities.WeatherCityEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getWeatherFromDb(): Flow<List<WeatherCityEntity>>
    suspend fun insertWeatherToDb(weatherEntities: List<WeatherCityEntity>)
    suspend fun addCityToFav(id: Int, isFav: Boolean)
    suspend fun searchCity(search: String): Flow<List<WeatherCityEntity>>
}
