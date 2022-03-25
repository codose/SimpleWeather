package com.android.weathersimple.domain.usecase

import com.android.weathersimple.data.repository.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetWeatherDataFromDbUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute() = weatherRepository.getWeatherFromDb()
}
