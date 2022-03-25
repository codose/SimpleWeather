package com.android.weathersimple.domain.usecase

import com.android.weathersimple.data.repository.WeatherRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute(search: String) = weatherRepository.searchCity(search)
}
