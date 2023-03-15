package pl.dtokarzewski.github

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.dtokarzewski.github.core.ui.R as CoreUiR
import pl.dtokarzewski.github.feature.repo.R as FeatureRepoR
import pl.dtokarzewski.github.feature.search.R as FeatureSearchR

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryName: String
    private lateinit var navigateUp: String
    private lateinit var repositoryDetails: String
    private lateinit var search: String
    private var repoName: String = "dtokarzewski/github"

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            repositoryName = getString(FeatureSearchR.string.repository_name)
            navigateUp = getString(CoreUiR.string.navigate_up)
            repositoryDetails = getString(FeatureRepoR.string.repo_details)
            search = getString(FeatureSearchR.string.search)
        }
    }

    @Test
    fun firstScreen_isSearch() {
        composeTestRule.apply {
            onNodeWithText(search).assertIsDisplayed()
        }
    }

    /* Verify navigation from SearchScreen to RepoScreen when user clicks Search button. */
    @Test
    fun navigateToRepo_repoScreenShowed() {
        composeTestRule.apply {
            // GIVEN Clear current input and put this repo name
            onNodeWithText(repositoryName).performTextClearance()
            onNodeWithText(repositoryName).performTextInput(repoName)
            // WHEN Navigate to RepoScreen
            onNodeWithText(search).performClick()
            // THEN verify RepoScreen visible
            onNodeWithText(repositoryDetails).assertIsDisplayed()
        }
    }

    /* Verify navigation back from RepoScreen to SearchScreen when user clicks TopAppBar back button. */
    @Test
    fun repoScreen_navigateUp_searchScreenShowed() {
        composeTestRule.apply {
            // GIVEN Clear current input and put this repo name and navigate to RepoScreen
            onNodeWithText(repositoryName).performTextClearance()
            onNodeWithText(repositoryName).performTextInput(repoName)
            onNodeWithText(search).performClick()
            // WHEN navigate up
            onNodeWithContentDescription(navigateUp).performClick()
            // THEN verify SearchScreen visible again
            onNodeWithText(search).assertIsDisplayed()
        }
    }
}