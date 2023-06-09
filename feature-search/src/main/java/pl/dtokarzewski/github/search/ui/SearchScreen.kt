package pl.dtokarzewski.github.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import pl.dtokarzewski.github.core.ui.ErrorSnackbar
import pl.dtokarzewski.github.core.ui.LocalSnackbarHostState
import pl.dtokarzewski.github.feature.search.R
import pl.dtokarzewski.githubbrowser.core.designsystem.Black40

@Composable
fun SearchRoute(
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
    SearchScreen(
        uiState = uiState,
        onRepoNameChanged = viewModel::onQueryChanged,
        onSearchClicked = viewModel::onSearchClicked,
        onRepoClicked = viewModel::onRecentRepoClicked,
        onErrorShown = viewModel::onErrorShown,
        snackbarHostState = snackbarHostState

    )
}

@OptIn(ExperimentalMaterial3Api::class)
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
            .padding(horizontal = 12.dp)
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
                .padding(vertical = 8.dp),
            enabled = uiState.isQueryValid,
            onClick = onSearchClicked,

            ) {
            Text(text = stringResource(id = R.string.search))
        }
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = stringResource(id = R.string.recent_repositories),
            style = MaterialTheme.typography.titleMedium
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
                        text = "${it.owner.login}/${it.name}"
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
        else -> { /* No-op */ }
    }
}

@Composable
fun ProgressIndicator() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Black40
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
            uiState = SearchUiState.Idle(query = "dtokarzewski/GitHub", true, emptyList()),
            onRepoNameChanged = {},
            onSearchClicked = {},
            onRepoClicked = {},
            onErrorShown = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}