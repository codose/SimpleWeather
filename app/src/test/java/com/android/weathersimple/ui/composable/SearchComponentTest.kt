package com.android.weathersimple.ui.composable

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.android.weathersimple.ui.theme.WeatherTheme
import com.android.weathersimple.utils.Constants.SEARCH_BUTTON_TEXT_TAG
import com.android.weathersimple.utils.Constants.TEXT_FIELD_TEXT_TAG
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `given when SearchComponent composable is displayed, data is entered and the search is clicked, assert that the query is returned`() {
        var actualText = ""
        val expectedQuery = "Hello there"
        composeTestRule.setContent {
            WeatherTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag(TEXT_FIELD_TEXT_TAG)
        val searchButton = composeTestRule.onNodeWithTag(SEARCH_BUTTON_TEXT_TAG)
        textField.performClick()
        textField.performTextInput(expectedQuery)

        searchButton.performClick()

        assertEquals(expectedQuery, actualText)
    }

    @Test
    fun `given when SearchComponent composable is displayed, data is entered and the user performs search from the keyboard, assert that the query is returned`() {
        var actualText = ""
        val expectedQuery = "Hello there"
        composeTestRule.setContent {
            WeatherTheme {
                SearchComponent {
                    actualText = it
                }
            }
        }

        val textField = composeTestRule.onNodeWithTag(TEXT_FIELD_TEXT_TAG)
        textField.performClick()
        textField.performTextInput(expectedQuery)
        textField.performImeAction()
        assertEquals(expectedQuery, actualText)
    }
}
