package pl.dtokarzewski.github.feature.repo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.dtokarzewski.github.core.model.Owner
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.feature.repo.R
import pl.dtokarzewski.github.core.ui.R as CoreUiR

@Composable
fun RepoRoute(
    viewModel: RepoViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    RepoScreen(
        uiState = uiState,
        navigateUp = navigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoScreen(
    uiState: RepoUiState,
    navigateUp: () -> Unit
) {
    val repo = (uiState as? RepoUiState.Success)?.repo
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = { Text(text = repo?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = CoreUiR.string.navigate_up)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
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
    }
}

@Composable
@Preview(name = "RepoScreen", device = "id:pixel_5")
fun RepoScreenPreview() {
    val repo = Repo(
        id = 1296269,
        name = "Hello-World",
        fullName = "octocat/Hello-World",
        description =  "This your first repo!",
        owner = Owner(login = "octocat", url = "https://api.github.com/users/octocat"),
        stars = 80
    )
    RepoScreen(
        uiState = RepoUiState.Success(repo),
        navigateUp = {}
    )
}