package com.android.weathersimple.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.android.weathersimple.ui.model.WeatherItem
import com.android.weathersimple.ui.theme.Gray
import com.android.weathersimple.ui.theme.Yellow
import com.android.weathersimple.utils.Constants.CARD_ITEM_TEXT_TAG

@ExperimentalCoilApi
@Composable
fun WeatherListItem(weatherItem: WeatherItem, onItemSelect: (WeatherItem) -> Unit, onItemFav: (Int, Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .testTag(CARD_ITEM_TEXT_TAG)
            .fillMaxWidth()
            .background(Gray.copy(alpha = 0.7f), RoundedCornerShape(16.dp))
            .wrapContentHeight()
            .clickable {
                onItemSelect(weatherItem)
            },
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = weatherItem.city, fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = weatherItem.date, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = weatherItem.time, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = weatherItem.temp, fontWeight = FontWeight.Bold, fontSize = 32.sp, color = Yellow)
                IconToggleButton(
                    checked = weatherItem.isFavourite,
                    onCheckedChange = {
                        onItemFav(weatherItem.id, it)
                    }
                ) {
                    if (weatherItem.isFavourite) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "", tint = Color.White)
                    } else {
                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "", tint = Color.White)
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherImage(url = weatherItem.weatherImage)

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = weatherItem.weatherName, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}
