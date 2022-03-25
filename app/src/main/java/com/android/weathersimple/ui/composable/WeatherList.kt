package com.android.weathersimple.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.android.weathersimple.ui.model.WeatherItem

@ExperimentalCoilApi
@Composable
fun WeatherList(weatherItems: List<WeatherItem>, onItemSelect: (WeatherItem) -> Unit, onItemFav: (Int, Boolean) -> Unit) {
    LazyColumn {
        items(
            weatherItems
        ) { result ->
            WeatherListItem(result, onItemSelect, onItemFav)
            Spacer(Modifier.height(16.dp))
        }
    }
}
