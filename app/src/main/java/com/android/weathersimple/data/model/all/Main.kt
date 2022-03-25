package com.android.weathersimple.data.model.all

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    val temp: Double
)
