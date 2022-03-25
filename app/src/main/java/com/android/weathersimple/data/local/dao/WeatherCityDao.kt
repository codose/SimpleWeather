package com.android.weathersimple.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.weathersimple.data.local.entities.WeatherCityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherCityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weatherCityEntity: WeatherCityEntity): Long

    @Query("SELECT * FROM weather_city ORDER by isFavourite DESC")
    fun getWeatherEntities(): Flow<List<WeatherCityEntity>>

    @Query("SELECT * FROM weather_city WHERE id = :id")
    suspend fun get(id: Int): WeatherCityEntity

    @Query("UPDATE weather_city SET isFavourite = :isFav WHERE id = :id")
    suspend fun updateCity(id: Int, isFav: Boolean)

    @Update()
    suspend fun update(weatherCityEntity: WeatherCityEntity)

    @Query("SELECT * FROM weather_city WHERE city LIKE '%' || :search || '%' ORDER by isFavourite DESC")
    fun searchCity(search: String): Flow<List<WeatherCityEntity>>
}
