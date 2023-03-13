package pl.dtokarzewski.github

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.dtokarzewski.github.core.ui.R as CoreUiR
import pl.dtokarzewski.github.feature.repo.R as FeatureRepoR
import pl.dtokarzewski.github.feature.search.R as FeatureSearchR

@HiltAndroidTest
class NavigationTest {

    @get:Rule()
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryName: String
    private lateinit var navigateUp: String
    private lateinit var repositoryDetails: String
    private lateinit var go: String
    private var repoName: String = "dtokarzewski/github"

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            repositoryName = getString(FeatureSearchR.string.repository_name)
            navigateUp = getString(CoreUiR.string.navigate_up)
            repositoryDetails = getString(FeatureRepoR.string.repo_details)
            go = getString(FeatureSearchR.string.go)
        }
    }

    @Test
    fun firstScreen_isSearch() {
        composeTestRule.apply {
            onNodeWithText(repositoryName).assertIsDisplayed()
        }
    }

    @Test
    fun navigateToRepo_repoScreenShowed() {
        composeTestRule.apply {
            onNodeWithText(repositoryName).performTextInput(repoName)
            onNodeWithText(go).performClick()
            onNodeWithText(repositoryDetails).assertIsDisplayed()
        }
    }
}