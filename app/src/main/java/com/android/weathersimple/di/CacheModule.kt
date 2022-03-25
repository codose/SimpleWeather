package com.android.weathersimple.di

import android.content.Context
import androidx.room.Room
import com.android.weathersimple.data.local.dao.WeatherCityDao
import com.android.weathersimple.data.local.database.WeatherDatabase
import com.android.weathersimple.data.source.local.LocalDataSource
import com.android.weathersimple.data.source.local.LocalDataSourceImpl
import com.android.weathersimple.utils.Constants.DATABASE_NAME
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun localDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: WeatherDatabase): WeatherCityDao {
        return db.weatherCityDao()
    }
}
