package pl.dtokarzewski.github.search.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.dtokarzewski.github.core.designsystem.GitHubTheme
import pl.dtokarzewski.github.feature.search.R

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToRepo: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    SearchScreen(
        uiState = uiState,
        onRepoNameChanged = viewModel::onRepoNameChanged,
        onSearchClicked = viewModel::onSearchClicked

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onRepoNameChanged: (String) -> Unit,
    onSearchClicked: () -> Unit
) {

    var repoName by remember { mutableStateOf(TextFieldValue(uiState.repoName)) }

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
            }
        )
        Button(
            modifier = Modifier
                .width(200.dp)
                .align(CenterHorizontally)
                .padding(vertical = 8.dp),
            enabled = repoName.text.isNotBlank(),
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

        }
    }
}

@Composable
@Preview(name = "SearchScreen", device = "id:pixel_5")
fun SearchScreenPreview() {
    GitHubTheme {
        SearchScreen(
            uiState = SearchUiState.Success(repoName = "dtokarzewski/GitHub"),
            onRepoNameChanged = {},
            onSearchClicked = {}
        )
    }
}