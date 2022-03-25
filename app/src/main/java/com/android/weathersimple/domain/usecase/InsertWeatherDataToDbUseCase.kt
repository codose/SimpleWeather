package com.android.weathersimple.domain.usecase

import com.android.weathersimple.data.repository.WeatherRepository
import com.android.weathersimple.domain.model.WeatherDomain
import javax.inject.Inject

class InsertWeatherDataToDbUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute(weatherDomain: List<WeatherDomain>) = weatherRepository.insertWeatherToDb(weatherDomain)
}
