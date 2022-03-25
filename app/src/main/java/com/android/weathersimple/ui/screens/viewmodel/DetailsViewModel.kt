package com.android.weathersimple.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weathersimple.domain.mapper.WeatherDetailsMapper
import com.android.weathersimple.domain.usecase.GetWeatherDetailsFromApiUseCase
import com.android.weathersimple.ui.model.WeatherDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val weatherMapper: WeatherDetailsMapper,
    private val getWeatherDetailsFromApiUseCase: GetWeatherDetailsFromApiUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Idle)
    val viewState = _viewState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _viewState.value = ViewState.Error(throwable.message ?: "An error occurred")
    }

    fun getWeatherDetails(weatherId: Int) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            val data = getWeatherDetailsFromApiUseCase
                .execute(weatherId)
            _viewState.value = ViewState.Success(
                weatherMapper.mapToWeatherList(data)
            )
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Success(val data: List<List<WeatherDetails>>) : ViewState()
        data class Error(val message: String) : ViewState()
        object Idle : ViewState()
    }
}
