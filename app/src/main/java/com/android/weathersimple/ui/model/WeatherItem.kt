package com.android.weathersimple.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherItem(
    val id: Int,
    val countryCode: String,
    val city: String,
    val temp: String,
    val weatherName: String,
    val weatherImage: String,
    val date: String,
    val time: String,
    val isFavourite: Boolean
) : Parcelable
