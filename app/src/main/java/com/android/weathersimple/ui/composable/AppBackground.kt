package com.android.weathersimple.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.android.weathersimple.R
import com.android.weathersimple.ui.theme.DarkBlue

@Composable
fun AppBackground(content: @Composable BoxScope.() -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Image(modifier = Modifier.fillMaxWidth(), painter = rememberImagePainter(data = R.drawable.landscape_day_mobile), contentDescription = "image_background", contentScale = ContentScale.Crop)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(DarkBlue)
            )
        }
        content()
    }
}
