package com.android.weathersimple.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.android.weathersimple.ui.model.WeatherItem
import com.google.gson.Gson

class AssetParamType : NavType<WeatherItem>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): WeatherItem? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): WeatherItem {
        return Gson().fromJson(value, WeatherItem::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: WeatherItem) {
        bundle.putParcelable(key, value)
    }
}
