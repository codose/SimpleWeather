package com.android.weathersimple.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.weathersimple.ui.composable.CurrentWeatherScreen
import com.android.weathersimple.ui.composable.ErrorPage
import com.android.weathersimple.ui.composable.FullScreenProgress
import com.android.weathersimple.ui.composable.WeatherForecastList
import com.android.weathersimple.ui.model.WeatherItem
import com.android.weathersimple.ui.screens.viewmodel.DetailsViewModel
import com.android.weathersimple.ui.theme.DarkBlue

@Composable
fun DetailsScreen(weatherItem: WeatherItem, onBack: () -> Unit) {
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val id = weatherItem.id
    Box(modifier = Modifier.background(DarkBlue)) {
        Box(modifier = Modifier.padding(20.dp)) {
            LaunchedEffect(key1 = "firstLaunch") {
                detailsViewModel.getWeatherDetails(id)
            }
            val viewState by detailsViewModel.viewState.collectAsState()

            Column(Modifier.fillMaxSize()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "", tint = Color.White)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(text = weatherItem.city, fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    }
                }
                CurrentWeatherScreen(weather = weatherItem)

                Spacer(modifier = Modifier.height(16.dp))

                when (viewState) {
                    is DetailsViewModel.ViewState.Error -> {
                        ErrorPage(modifier = Modifier.fillMaxSize(), message = (viewState as DetailsViewModel.ViewState.Error).message) {
                            detailsViewModel.getWeatherDetails(id)
                        }
                    }
                    DetailsViewModel.ViewState.Idle -> {
                    }
                    DetailsViewModel.ViewState.Loading -> {
                        FullScreenProgress(modifier = Modifier.fillMaxSize())
                    }
                    is DetailsViewModel.ViewState.Success -> {
                        WeatherForecastList((viewState as DetailsViewModel.ViewState.Success).data)
                    }
                }
            }
        }
    }
}
