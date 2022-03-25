package com.android.weathersimple.data.source.remote

import com.android.weathersimple.data.api.WeatherApi
import com.android.weathersimple.data.model.all.WeatherResponseSchema
import com.android.weathersimple.data.model.details.WeatherDetailsResponseSchema
import com.android.weathersimple.utils.Constants.APP_ID
import com.android.weathersimple.utils.Constants.COUNTRY_IDS
import com.android.weathersimple.utils.Constants.DEFAULT_UNIT
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: WeatherApi) : RemoteDataSource {
    override suspend fun getAllWeatherData(): WeatherResponseSchema = api.getWeatherData(
        groupIds = COUNTRY_IDS,
        appId = APP_ID,
        units = DEFAULT_UNIT
    )

    override suspend fun getWeatherDetails(id: Int): WeatherDetailsResponseSchema = api.getWeatherDetails(
        id = id,
        appId = APP_ID,
        units = DEFAULT_UNIT
    )
}
