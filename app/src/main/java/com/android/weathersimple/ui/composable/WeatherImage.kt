package com.android.weathersimple.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.android.weathersimple.ui.theme.Blue
import com.android.weathersimple.ui.theme.Gray

@Composable
fun WeatherImage(url: String) {
    Box(
        Modifier
            .wrapContentSize()
            .background(
                brush = Brush.linearGradient(colors = listOf(Blue, Gray)),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = rememberImagePainter(url),
            contentDescription = "Weather Image"
        )
    }
}
