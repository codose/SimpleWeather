package com.android.weathersimple.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {
    private const val YYYY_MM_DD = "E, dd MMM yyyy"

    private const val HH_MM = "HH:mm"

    const val MILLIS_IN_MIN = 1000L

    private fun Long.formatDateString(format: String): String {
        val formatter = SimpleDateFormat(format, Locale.ENGLISH)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this
        return formatter.format(this)
    }

    fun Long.formatMillisToDateString(): String {
        return formatDateString(YYYY_MM_DD)
    }

    fun Long.formatMillisToTimeString(): String {
        return formatDateString(HH_MM)
    }
}
