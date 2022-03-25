package com.android.weathersimple.domain.mapper

import com.android.weathersimple.data.local.entities.WeatherCityEntity
import com.android.weathersimple.data.model.all.WeatherSchema
import com.android.weathersimple.domain.model.WeatherDomain
import com.android.weathersimple.ui.model.WeatherItem
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class WeatherMapperTest {
    private val sut = WeatherMapper()

    private val mockedIconUrl = "mockUrl"
    private val mockedId = 98
    private val mockedCountryCode = "mockedCountryCode"
    private val mockedCity = "mockedCity"
    private val mockedTemp = 12.0
    private val mockedWeatherName = "mockedWeatherName"
    private val mockedDateTime = 526262

    private val mockedWeatherSchema = mockk<WeatherSchema>().apply {
        every { sys.country } returns mockedCountryCode
        every { id } returns mockedId
        every { name } returns mockedCity
        every { main.temp } returns mockedTemp
        every { dt } returns mockedDateTime
        every { weather.first().main } returns mockedWeatherName
        every { weather.first().icon } returns mockedIconUrl
    }

    private val mockedWeatherDomain = WeatherDomain(
        id = mockedId,
        countryCode = mockedCountryCode,
        city = mockedCity,
        temp = mockedTemp,
        weatherName = mockedWeatherName,
        weatherImage = mockedIconUrl,
        dateTime = mockedDateTime.toLong(),
        isFavourite = false
    )

    private val mockedWeatherEntity = WeatherCityEntity(
        id = mockedId,
        countryCode = mockedCountryCode,
        city = mockedCity,
        temp = mockedTemp,
        weatherName = mockedWeatherName,
        weatherImage = mockedIconUrl,
        dateTime = mockedDateTime.toLong(),
        isFavourite = false
    )

    private val mockedWeatherItem = WeatherItem(
        id = mockedId,
        countryCode = mockedCountryCode,
        city = mockedCity,
        temp = "$mockedTemp \u2103",
        weatherName = mockedWeatherName,
        weatherImage = "https://openweathermap.org/img/w/$mockedIconUrl.png",
        date = "mockedDateTime.toLong()",
        time = "526726762",
        isFavourite = false
    )

    @Test
    fun `test schema mapping`() {
        val actualResult = sut.mapToDomain(mockedWeatherSchema)
        assertEquals(mockedWeatherDomain, actualResult)
    }

    @Test
    fun `test domain mapping`() {
        val actualWeatherItem = sut.mapToPresentation(mockedWeatherDomain)
        val actualWeatherEntity = sut.mapToEntity(mockedWeatherDomain)
        val actualDomain = sut.mapToDomain(mockedWeatherEntity)
        assertEquals(mockedWeatherItem, actualWeatherItem)
        assertEquals(mockedWeatherEntity, actualWeatherEntity)
        assertEquals(mockedWeatherDomain, actualDomain)
    }
}