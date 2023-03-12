package pl.dtokarzewski.github.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import pl.dtokarzewski.github.core.designsystem.GitHubTheme
import pl.dtokarzewski.github.core.ui.LocalSnackbarHostState

@Composable
fun GitHubContent(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    CompositionLocalProvider(
        LocalSnackbarHostState provides snackbarHostState
    ) {
        GitHubTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

            }
        }
    }
}