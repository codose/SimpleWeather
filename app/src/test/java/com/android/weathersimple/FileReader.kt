package com.android.weathersimple

import java.io.InputStreamReader

object FileReader {
    fun readStringFromFile(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(fileName)).use { it.readText() }
    }
}
