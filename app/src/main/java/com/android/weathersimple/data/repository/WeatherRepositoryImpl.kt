package com.android.weathersimple.data.repository

import com.android.weathersimple.data.source.local.LocalDataSource
import com.android.weathersimple.data.source.remote.RemoteDataSource
import com.android.weathersimple.domain.mapper.WeatherDetailsMapper
import com.android.weathersimple.domain.mapper.WeatherMapper
import com.android.weathersimple.domain.model.WeatherDomain
import com.android.weathersimple.domain.model.details.WeatherDetailsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherMapper: WeatherMapper,
    private val weatherDetailsMapper: WeatherDetailsMapper,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {
    override suspend fun getWeatherDataFromAPi(): List<WeatherDomain> {
        return remoteDataSource.getAllWeatherData().list.map {
            weatherMapper.mapToDomain(it)
        }
    }

    override suspend fun getWeatherDetailsFromAPi(id: Int): Map<String, List<WeatherDetailsDomain>> {
        return weatherDetailsMapper.mapToDomain(remoteDataSource.getWeatherDetails(id))
    }

    override suspend fun insertWeatherToDb(items: List<WeatherDomain>) {
        localDataSource.insertWeatherToDb(items.map { weatherMapper.mapToEntity(it) })
    }

    override suspend fun getWeatherFromDb(): Flow<List<WeatherDomain>> {
        return localDataSource.getWeatherFromDb().map {
            it.map { entity ->
                weatherMapper.mapToDomain(entity)
            }
        }
    }

    override suspend fun addCityToFavorite(id: Int, isFav: Boolean) {
        localDataSource.addCityToFav(id, isFav)
    }

    override suspend fun searchCity(search: String): Flow<List<WeatherDomain>> {
        return localDataSource.searchCity(search).map {
            it.map { entity ->
                weatherMapper.mapToDomain(entity)
            }
        }
    }
}
