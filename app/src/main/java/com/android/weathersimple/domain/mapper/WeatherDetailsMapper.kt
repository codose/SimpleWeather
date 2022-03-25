package com.android.weathersimple.domain.mapper

import com.android.weathersimple.utils.DateUtils.formatMillisToDateString
import com.android.weathersimple.utils.DateUtils.formatMillisToTimeString
import com.android.weathersimple.data.model.details.City
import com.android.weathersimple.data.model.details.WeatherDetailsResponseSchema
import com.android.weathersimple.data.model.details.WeatherSchema
import com.android.weathersimple.domain.model.details.WeatherDetailsDomain
import com.android.weathersimple.ui.model.WeatherDetails
import com.android.weathersimple.utils.DateUtils.MILLIS_IN_MIN
import javax.inject.Inject

class WeatherDetailsMapper @Inject constructor() {
    fun mapToDomain(schema: WeatherDetailsResponseSchema): Map<String, List<WeatherDetailsDomain>> =
        schema.list.map { mapToDomain(schema.city, it) }.groupBy { it.date }

    private fun mapToDomain(city: City, weatherSchema: WeatherSchema): WeatherDetailsDomain = WeatherDetailsDomain(
        city = city.name,
        time = weatherSchema.dt.toLong().times(MILLIS_IN_MIN).formatMillisToTimeString(),
        temperature = weatherSchema.main.temp,
        weather = weatherSchema.weather.first().main,
        date = weatherSchema.dt.toLong().times(MILLIS_IN_MIN).formatMillisToDateString(),
        weatherImage = weatherSchema.weather.first().icon
    )

    private fun mapToPresentation(domain: WeatherDetailsDomain) = WeatherDetails(
        city = domain.city,
        temperature = "${domain.temperature} \u2103",
        time = domain.time,
        date = domain.date,
        weather = domain.weather,
        weatherImage = "https://openweathermap.org/img/w/${domain.weatherImage}.png"
    )

    fun mapToWeatherList(weatherList: Map<String, List<WeatherDetailsDomain>>): List<List<WeatherDetails>> {
        return weatherList.map {
            it.value.map { domain ->
                mapToPresentation(domain)
            }
        }
    }
}
