package com.android.weathersimple.ui.screens.viewmodel

import com.android.weathersimple.CoroutineTestRule
import com.android.weathersimple.dispatchers.TestDispatcherProvider
import com.android.weathersimple.domain.mapper.WeatherMapper
import com.android.weathersimple.domain.usecase.AddCityToFavUseCase
import com.android.weathersimple.domain.usecase.GetAllWeatherDataFromApiUseCase
import com.android.weathersimple.domain.usecase.GetWeatherDataFromDbUseCase
import com.android.weathersimple.domain.usecase.InsertWeatherDataToDbUseCase
import com.android.weathersimple.domain.usecase.SearchCityUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var sut: HomeViewModel
    private val weatherMapper = WeatherMapper()
    private val searchQuery = "searchQuery"
    private val getAllWeatherDataFromApiUseCase = mockk<GetAllWeatherDataFromApiUseCase>().apply {
        coEvery { execute() } returns listOf()
    }
    private val insertWeatherDataToDbUseCase = mockk<InsertWeatherDataToDbUseCase>().apply {
        coEvery { execute(any()) } returns Unit
    }
    private val getWeatherDataFromDbUseCase = mockk<GetWeatherDataFromDbUseCase>().apply {
        coEvery { execute() } returns flowOf()
    }
    private val addCityToFavUseCase = mockk<AddCityToFavUseCase>().apply {
        coEvery { execute(any(), any()) } returns Unit
    }
    private val searchCityUseCase = mockk<SearchCityUseCase>().apply {
        coEvery { execute(searchQuery) } returns flowOf()
    }

    private val coroutineProvider = TestDispatcherProvider()

    @Before
    fun setUp() {
        sut = HomeViewModel(
            weatherMapper,
            getAllWeatherDataFromApiUseCase,
            insertWeatherDataToDbUseCase,
            getWeatherDataFromDbUseCase,
            addCityToFavUseCase,
            searchCityUseCase,
            coroutineProvider
        )
    }

    @Test
    fun `given when viewmodel is initialized, verify that all required usecases is executed`() = runTest {

        coVerifySequence {
            getAllWeatherDataFromApiUseCase.execute()
            insertWeatherDataToDbUseCase.execute(listOf())
            getWeatherDataFromDbUseCase.execute()
        }
    }

    @Test
    fun `given when searchCity is called, verify that SearchCityUseCase is executed`() = runTest {
        sut.searchCity(searchQuery)
        coVerify {
            searchCityUseCase.execute(searchQuery)
        }
    }

    @Test
    fun `given when addToFav is called, verify that AddToFavUseCase is executed`() = runTest {
        sut.addCityToFavorite(1, true)
        coVerify {
            addCityToFavUseCase.execute(1, true)
        }
    }
}
