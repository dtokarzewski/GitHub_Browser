package pl.dtokarzewski.github.feature.repo.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import pl.dtokarzewski.github.core.designsystem.GitHubTheme
import pl.dtokarzewski.github.core.designsystem.component.GitHubAppBar
import pl.dtokarzewski.github.core.model.Commit
import pl.dtokarzewski.github.core.model.testdata.allCommitsTestData
import pl.dtokarzewski.github.core.model.testdata.repoTestData
import pl.dtokarzewski.github.feature.repo.R
import pl.dtokarzewski.github.core.ui.R as coreUiR

@Composable
fun RepoScreen(
    viewModel: RepoViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val commits = viewModel.commits.collectAsLazyPagingItems()

    GitHubTheme {
        RepoScreen(
            uiState = uiState,
            commits = commits,
            navigateUp = navigateUp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoScreen(
    uiState: RepoUiState,
    commits: LazyPagingItems<Commit>,
    navigateUp: () -> Unit
) {
    val repo = (uiState as? RepoUiState.Success)?.repo
    Scaffold(
        topBar = {
            GitHubAppBar(
                modifier = Modifier,
                title =  repo?.name ?: "",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconContentDescription = stringResource(coreUiR.string.navigate_up),
                onNavigationClick = navigateUp,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = stringResource(id = R.string.repo_details),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = stringResource(
                        R.string.repo_id,
                        repo?.id ?: 0
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = stringResource(
                        R.string.repo_stars,
                        repo?.stars ?: 0
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            HorizontalDivider()

            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(count = commits.itemCount) { index ->
                    commits[index]?.let { commit ->
                        Text(
                            modifier = Modifier.padding(vertical = 12.dp),
                            text = stringResource(
                                id = R.string.commit_sha,
                                commit.sha.take(9)
                            ),
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 12.dp),
                            text = stringResource(
                                id = R.string.commit_date,
                                commit.commit.author.date,
                            ),
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 12.dp),
                            text = stringResource(
                                R.string.commit_message,
                                commit.commit.message,
                            ),
                            fontWeight = FontWeight.W400,
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
fun RepoScreenPreview() {
    GitHubTheme {
        RepoScreen(
            uiState = RepoUiState.Success(repoTestData()),
            commits = flowOf(PagingData.from(allCommitsTestData())).collectAsLazyPagingItems(),
            navigateUp = {}
        )
    }
}
