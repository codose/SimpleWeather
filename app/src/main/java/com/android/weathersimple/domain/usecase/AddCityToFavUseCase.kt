package com.android.weathersimple.domain.usecase

import com.android.weathersimple.data.repository.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AddCityToFavUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute(id: Int, isFav: Boolean) = weatherRepository.addCityToFavorite(id, isFav)
}
