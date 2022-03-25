package com.android.weathersimple.domain.mapper

import com.android.weathersimple.data.local.entities.WeatherCityEntity
import com.android.weathersimple.data.model.all.WeatherSchema
import com.android.weathersimple.domain.model.WeatherDomain
import com.android.weathersimple.ui.model.WeatherItem
import com.android.weathersimple.utils.DateUtils.MILLIS_IN_MIN
import com.android.weathersimple.utils.DateUtils.formatMillisToDateString
import com.android.weathersimple.utils.DateUtils.formatMillisToTimeString
import javax.inject.Inject

class WeatherMapper @Inject constructor() {
    fun mapToDomain(schema: WeatherSchema): WeatherDomain = WeatherDomain(
        id = schema.id,
        countryCode = schema.sys.country,
        city = schema.name,
        temp = schema.main.temp,
        weatherName = schema.weather.first().main,
        weatherImage = schema.weather.first().icon,
        dateTime = schema.dt.toLong()
    )

    fun mapToPresentation(domain: WeatherDomain): WeatherItem = WeatherItem(
        id = domain.id,
        countryCode = domain.countryCode,
        city = domain.city,
        temp = "${domain.temp} ℃",
        weatherName = domain.weatherName,
        weatherImage = "https://openweathermap.org/img/w/${domain.weatherImage}.png",
        date = domain.dateTime.times(MILLIS_IN_MIN).formatMillisToDateString(),
        time = domain.dateTime.times(MILLIS_IN_MIN).formatMillisToTimeString(),
        isFavourite = domain.isFavourite
    )

    fun mapToEntity(domain: WeatherDomain): WeatherCityEntity = WeatherCityEntity(
        id = domain.id,
        countryCode = domain.countryCode,
        city = domain.city,
        temp = domain.temp,
        weatherName = domain.weatherName,
        weatherImage = domain.weatherImage,
        isFavourite = domain.isFavourite,
        dateTime = domain.dateTime
    )

    fun mapToDomain(entity: WeatherCityEntity): WeatherDomain = WeatherDomain(
        id = entity.id,
        countryCode = entity.countryCode,
        city = entity.city,
        temp = entity.temp,
        weatherName = entity.weatherName,
        weatherImage = entity.weatherImage,
        dateTime = entity.dateTime,
        isFavourite = entity.isFavourite
    )
}
