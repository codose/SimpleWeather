package com.android.weathersimple.data.source.local

import com.android.weathersimple.data.local.dao.WeatherCityDao
import com.android.weathersimple.data.local.entities.WeatherCityEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val weatherCityDao: WeatherCityDao) : LocalDataSource {
    companion object {
        private const val INSERT_FAILURE_ID = -1L
    }

    override suspend fun getWeatherFromDb(): Flow<List<WeatherCityEntity>> = weatherCityDao.getWeatherEntities()

    override suspend fun insertWeatherToDb(weatherEntities: List<WeatherCityEntity>) {
        weatherEntities.forEach {
            val insert = weatherCityDao.insert(it)
            if (insert == INSERT_FAILURE_ID) {
                update(it)
            }
        }
    }

    private suspend fun update(weather: WeatherCityEntity) {
        val weatherInDb = weatherCityDao.get(weather.id)
        val updated = weather.copy(isFavourite = weatherInDb.isFavourite)
        weatherCityDao.update(updated)
    }

    override suspend fun addCityToFav(id: Int, isFav: Boolean) = weatherCityDao.updateCity(id, isFav)

    override suspend fun searchCity(search: String): Flow<List<WeatherCityEntity>> = weatherCityDao.searchCity(search)
}
