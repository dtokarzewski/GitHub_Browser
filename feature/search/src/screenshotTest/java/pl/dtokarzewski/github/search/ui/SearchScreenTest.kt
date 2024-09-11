package pl.dtokarzewski.github.search.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pl.dtokarzewski.github.core.designsystem.GitHubTheme

class SearchScreenTest {

    @Preview()
    @Composable
    fun SearchScreenPreview() {
        GitHubTheme {
            SearchScreen(
                uiState = SearchUiState.Idle(
                    query = "dtokarzewski/GitHub",
                    isQueryValid = true,
                    allRepos = emptyList()),
                onRepoNameChanged = {},
                onSearchClicked = {},
                onRepoClicked = {},
                onErrorShown = {},
                snackbarHostState = SnackbarHostState()
            )
        }
    }
}