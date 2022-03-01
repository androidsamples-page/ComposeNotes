package co.icanteach.apps.android.composenotes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import co.icanteach.apps.android.composenotes.home.HomeScreen
import co.icanteach.apps.android.composenotes.ui.theme.ComposeNotesTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_CreateNote_Displayed() {
        // Start the app
        composeTestRule.setContent {
            val navController = rememberNavController()
            ComposeNotesTheme {
                HomeScreen(navController = navController)
            }
        }

        composeTestRule.onNodeWithText("NEW NOTE").assertIsDisplayed()
    }

    @Test
    fun test_CreateNote_Enabled() {
        // Start the app
        composeTestRule.setContent {
            val navController = rememberNavController()
            ComposeNotesTheme {
                HomeScreen(navController = navController)
            }
        }

        composeTestRule.onNodeWithText("NEW NOTE").assertIsEnabled()
    }
}