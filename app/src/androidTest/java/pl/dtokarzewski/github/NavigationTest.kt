package pl.dtokarzewski.github

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.dtokarzewski.github.feature.search.R as FeatureSearchR

@HiltAndroidTest
class NavigationTest {

    @get:Rule()
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryName: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            repositoryName = getString(FeatureSearchR.string.repository_name)
        }
    }

    @Test
    fun firstScreen_isSearch() {
        composeTestRule.apply {
            onNodeWithText(repositoryName).assertIsDisplayed()
        }
    }
}