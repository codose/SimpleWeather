package com.android.weathersimple.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.weathersimple.data.local.dao.WeatherCityDao
import com.android.weathersimple.data.local.entities.WeatherCityEntity

@Database(
    entities = [WeatherCityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherCityDao(): WeatherCityDao
}
