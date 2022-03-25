package com.android.weathersimple.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.weathersimple.ui.model.WeatherItem
import com.android.weathersimple.ui.theme.Gray
import com.android.weathersimple.ui.theme.Yellow

@Composable
fun CurrentWeatherScreen(weather: WeatherItem) {
    Box(modifier = Modifier.background(color = Gray.copy(alpha = 0.7f), shape = RoundedCornerShape(12.dp))) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Current Weather", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = weather.date, fontSize = 16.sp, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = weather.time, fontSize = 16.sp, color = Color.White)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = weather.temp, fontSize = 32.sp, fontWeight = FontWeight.Medium, color = Yellow)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = weather.weatherName, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Yellow)
            }
            Spacer(modifier = Modifier.height(12.dp))
            WeatherImage(url = weather.weatherImage)
        }
    }
}
