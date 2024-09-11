package pl.dtokarzewski.github.feature.repo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import pl.dtokarzewski.github.core.designsystem.GitHubTheme
import pl.dtokarzewski.github.core.model.testdata.commitsTestData
import pl.dtokarzewski.github.core.model.testdata.repoTestData

class RepoScreenTest {

    @Preview()
    @Composable
    fun RepoScreenPreview() {
        GitHubTheme {
            RepoScreen(
                uiState = RepoUiState.Success(repoTestData()),
                commits = flowOf(PagingData.from(commitsTestData())).collectAsLazyPagingItems(),
                navigateUp = {}
            )
        }
    }
}
