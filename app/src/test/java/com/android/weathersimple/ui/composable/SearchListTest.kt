package com.android.weathersimple.ui.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.android.weathersimple.ui.model.WeatherItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockedCity = "Lagos"
    private val mockedCity2 = "Benin"

    private val mockedSearchItem = WeatherItem(
        id = 0,
        countryCode = "",
        city = mockedCity,
        temp = "",
        weatherName = "",
        weatherImage = "",
        date = "",
        time = "",
        isFavourite = false
    )

    private val mockedSearchItem2 = WeatherItem(
        id = 1,
        countryCode = "",
        city = mockedCity2,
        temp = "",
        weatherName = "",
        weatherImage = "",
        date = "",
        time = "",
        isFavourite = false
    )

    @Test
    fun `given WeatherList Composable when Items is available, then the correct data should be displayed`() {
        val searchItems = listOf(mockedSearchItem, mockedSearchItem2)

        composeTestRule.setContent {
            WeatherList(
                searchItems,
                onItemSelect = {
                },
                onItemFav = { _, _ ->
                }
            )
        }
        composeTestRule.onNodeWithText(mockedCity).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockedCity2).assertIsDisplayed()
    }
}
