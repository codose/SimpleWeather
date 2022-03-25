package com.android.weathersimple.di

import com.android.weathersimple.data.repository.WeatherRepository
import com.android.weathersimple.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGithubRepository(githubSearchRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}
