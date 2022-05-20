package com.app.imdbclone

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.imdbclone.ui.composables.MovieLoadedView
import com.app.imdbclone.ui.composables.MovieTile
import com.app.imdbclone.ui.state.MovieUiData
import com.app.imdbclone.ui.theme.ImdbCloneTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ComposeTests {

    @get:Rule
    private val composeTestRule = createComposeRule()

    @Test
    fun setUp() {
        composeTestRule.setContent {
            ImdbCloneTheme {
              MovieTile(movieTileData = MovieUiData(imageUrl = "", title = "test") ) {
              }
            }
        }
        composeTestRule.onNodeWithText("test").assertIsDisplayed()
    }

}