package com.android.weathersimple.domain.usecase

import com.android.weathersimple.data.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchCityUseCaseTest {
    private lateinit var sut: SearchCityUseCase
    private val weatherRepository = mockk<WeatherRepository>()

    @Test
    fun `given query, when execute is called, then githubSearchRepository searchUser should be called`() = runTest {
        sut = SearchCityUseCase(weatherRepository)
        val query = "searchQuery"
        coEvery {
            weatherRepository.searchCity(query)
        } returns flowOf()

        sut.execute(query)

        coVerify {
            weatherRepository.searchCity(query)
        }
    }
}
