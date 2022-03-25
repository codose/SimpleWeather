package com.android.weathersimple.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.android.weathersimple.ui.composable.AppBackground
import com.android.weathersimple.ui.composable.ErrorPage
import com.android.weathersimple.ui.composable.FullScreenProgress
import com.android.weathersimple.ui.composable.SearchComponent
import com.android.weathersimple.ui.composable.WeatherList
import com.android.weathersimple.ui.model.WeatherItem
import com.android.weathersimple.ui.screens.viewmodel.HomeViewModel
import com.android.weathersimple.utils.showToast

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(navigateToDetails: (WeatherItem) -> Unit) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    AppBackground {
        val viewState by homeViewModel.viewState.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            SearchComponent {
                homeViewModel.searchCity(it)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(Modifier.padding(top = 20.dp, end = 20.dp, start = 20.dp)) {
                when (viewState) {
                    HomeViewModel.ViewState.Loading -> {
                        FullScreenProgress(modifier = Modifier.fillMaxSize())
                    }
                    is HomeViewModel.ViewState.Success -> {
                        val state = viewState as HomeViewModel.ViewState.Success
                        WeatherList(state.data, navigateToDetails) { id, isFav ->
                            homeViewModel.addCityToFavorite(id, isFav)
                        }
                        state.errorMessage?.let {
                            LocalContext.current.showToast(it)
                        }
                    }
                    is HomeViewModel.ViewState.Error -> {
                        ErrorPage(message = (viewState as HomeViewModel.ViewState.Error).message, modifier = Modifier.fillMaxSize()) {
                            homeViewModel.getAllWeatherDataFromApi()
                        }
                    }
                    HomeViewModel.ViewState.Idle -> {
                    }
                }
            }
        }
    }
}
