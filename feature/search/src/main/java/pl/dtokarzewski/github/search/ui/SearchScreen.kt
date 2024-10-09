package pl.dtokarzewski.github.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.dtokarzewski.github.core.designsystem.GitHubTheme
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.model.testdata.allReposTestData
import pl.dtokarzewski.github.core.ui.ErrorSnackbar
import pl.dtokarzewski.github.core.ui.LocalSnackbarHostState
import pl.dtokarzewski.github.feature.search.R

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToRepo: (String, String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = LocalSnackbarHostState.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is SearchUiState.NavigateToRepo -> {
                navigateToRepo(
                    (uiState as SearchUiState.NavigateToRepo).owner,
                    (uiState as SearchUiState.NavigateToRepo).name
                )
                viewModel.onNavigated()
            }
            else -> { /* No-op */
            }
        }
    }
    GitHubTheme {
        SearchScreen(
            uiState = uiState,
            onRepoNameChanged = viewModel::onQueryChanged,
            onSearchClicked = viewModel::onSearchClicked,
            onRepoClicked = viewModel::onRecentRepoClicked,
            onErrorShown = viewModel::onErrorShown,
            snackbarHostState = snackbarHostState

        )
    }
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onRepoNameChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onRepoClicked: (Repo) -> Unit,
    onErrorShown: () -> Unit,
    snackbarHostState: SnackbarHostState
) {

    var repoName by remember { mutableStateOf(TextFieldValue(uiState.query)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            value = repoName,
            label = { Text(text = stringResource(id = R.string.repository_name)) },
            placeholder = { Text(text = stringResource(id = R.string.repository_name_hint)) },
            onValueChange = {
                repoName = it
                onRepoNameChanged(it.text)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { onSearchClicked() }
            )
        )
        Button(
            modifier = Modifier
                .width(200.dp)
                .align(CenterHorizontally)
                .padding(vertical = 16.dp),
            enabled = uiState.isQueryValid,
            onClick = onSearchClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
            ),
        ) {
            Text(
                text = stringResource(id = R.string.search),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = stringResource(id = R.string.recent_repositories),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            uiState.allRepos.forEach {
                item {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .clickable { onRepoClicked(it) },
                        text = "${it.owner.login}/${it.name}",
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    }

    when (uiState) {
        is SearchUiState.Loading -> {
            ProgressIndicator()
        }

        is SearchUiState.Error -> {
            ErrorSnackbar(
                snackbarHostState = snackbarHostState,
                error = uiState.error,
                onErrorShown = onErrorShown
            )
        }

        else -> { /* No-op */
        }
    }
}

@Composable
fun ProgressIndicator() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
    ) {
        Box {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp)
                    .clipToBounds()
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview(name = "SearchScreen", device = "id:pixel_5")
fun SearchScreenPreview() {
    GitHubTheme {
        SearchScreen(
            uiState = SearchUiState.Loading(
                query = "dtokarzewski/GitHub",
                isQueryValid = true,
                allRepos = allReposTestData()
            ),
            onRepoNameChanged = {},
            onSearchClicked = {},
            onRepoClicked = {},
            onErrorShown = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}