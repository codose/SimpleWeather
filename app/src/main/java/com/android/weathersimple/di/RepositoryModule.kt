package com.android.weathersimple.di

import com.android.weathersimple.data.repository.WeatherRepository
import com.android.weathersimple.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepositoryImpl(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}
