package pl.dtokarzewski.github.search.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pl.dtokarzewski.github.core.designsystem.GitHubTheme
import pl.dtokarzewski.github.core.model.testdata.repoTestData

class SearchScreenTest {

    @Preview()
    @Composable
    fun SearchScreenPreview() {
        GitHubTheme {
            SearchScreen(
                uiState = SearchUiState.Idle(
                    query = "dtokarzewski/GitHub",
                    isQueryValid = true,
                    allRepos = listOf(repoTestData())
                ),
                onRepoNameChanged = {},
                onSearchClicked = {},
                onRepoClicked = {},
                onErrorShown = {},
                snackbarHostState = SnackbarHostState()
            )
        }
    }

    @Preview()
    @Composable
    fun SearchScreenInvalidStateQueryPreview() {
        GitHubTheme {
            SearchScreen(
                uiState = SearchUiState.Idle(
                    query = "",
                    isQueryValid = false,
                    allRepos = listOf(repoTestData())
                ),
                onRepoNameChanged = {},
                onSearchClicked = {},
                onRepoClicked = {},
                onErrorShown = {},
                snackbarHostState = SnackbarHostState()
            )
        }
    }

    @Preview()
    @Composable
    fun SearchScreenLoadingStatePreview() {
        GitHubTheme {
            SearchScreen(
                uiState = SearchUiState.Loading(
                    query = "dtokarzewski/GitHub",
                    isQueryValid = true,
                    allRepos = listOf(repoTestData())
                ),
                onRepoNameChanged = {},
                onSearchClicked = {},
                onRepoClicked = {},
                onErrorShown = {},
                snackbarHostState = SnackbarHostState()
            )
        }
    }
}