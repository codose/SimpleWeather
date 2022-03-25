package com.android.weathersimple.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weathersimple.domain.mapper.WeatherMapper
import com.android.weathersimple.domain.model.WeatherDomain
import com.android.weathersimple.domain.usecase.AddCityToFavUseCase
import com.android.weathersimple.domain.usecase.GetAllWeatherDataFromApiUseCase
import com.android.weathersimple.domain.usecase.GetWeatherDataFromDbUseCase
import com.android.weathersimple.domain.usecase.InsertWeatherDataToDbUseCase
import com.android.weathersimple.domain.usecase.SearchCityUseCase
import com.android.weathersimple.ui.model.WeatherItem
import com.android.weathersimple.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherMapper: WeatherMapper,
    private val getAllWeatherDataFromApiUseCase: GetAllWeatherDataFromApiUseCase,
    private val insertWeatherDataToDbUseCase: InsertWeatherDataToDbUseCase,
    private val getWeatherDataFromDbUseCase: GetWeatherDataFromDbUseCase,
    private val addCityToFavUseCase: AddCityToFavUseCase,
    private val searchCityUseCase: SearchCityUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Idle)
    val viewState = _viewState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        getWeatherFromDb(throwable.message)
    }

    init {
        getAllWeatherDataFromApi()
    }

    fun getAllWeatherDataFromApi() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io + coroutineExceptionHandler) {
            val data = getAllWeatherDataFromApiUseCase.execute()
            insertWeatherDataToDbUseCase.execute(data)
            getWeatherFromDb()
        }
    }

    fun addCityToFavorite(id: Int, isFav: Boolean) {
        viewModelScope.launch(dispatchers.io) {
            addCityToFavUseCase.execute(id, isFav)
        }
    }

    fun searchCity(search: String) {
        viewModelScope.launch {
            searchCityUseCase
                .execute(search)
                .collect {
                    showData(it, null)
                }
        }
    }

    private fun getWeatherFromDb(errorMessage: String? = null) {
        viewModelScope.launch(dispatchers.main) {
            getWeatherDataFromDbUseCase
                .execute()
                .collect {
                    showData(it, errorMessage)
                }
        }
    }

    private fun showData(weathers: List<WeatherDomain>, errorMessage: String?) {
        val data = weathers.map { domain ->
            weatherMapper.mapToPresentation(domain)
        }
        if (data.isNotEmpty()) {
            _viewState.value = ViewState.Success(data, errorMessage)
        } else {
            _viewState.value = ViewState.Error("No City Found")
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Success(val data: List<WeatherItem>, val errorMessage: String?) : ViewState()
        data class Error(val message: String) : ViewState()
        object Idle : ViewState()
    }
}
