package com.android.weathersimple.ui.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.android.weathersimple.ui.theme.WeatherTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ErrorPageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given when ErrorPage composable is used, it should display the correct text`() {
        val mockedError = "Error Message"
        composeTestRule.setContent {
            WeatherTheme {
                ErrorPage(message = mockedError) {}
            }
        }

        composeTestRule.onNodeWithText(mockedError).assertIsDisplayed()

        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
    }
}
