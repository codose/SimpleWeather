package com.android.weathersimple.domain.usecase

import com.android.weathersimple.data.repository.WeatherRepository
import javax.inject.Inject

class GetAllWeatherDataFromApiUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute() = weatherRepository.getWeatherDataFromAPi()
}
