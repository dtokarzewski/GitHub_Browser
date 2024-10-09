package pl.dtokarzewski.github.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import pl.dtokarzewski.github.core.designsystem.GitHubTheme
import pl.dtokarzewski.github.core.ui.LocalSnackbarHostState
import pl.dtokarzewski.github.navigation.GitHubNavHost

@Composable
fun GitHubContent(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalSnackbarHostState provides snackbarHostState
    ) {
        GitHubTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) {
                GitHubNavHost(
                    modifier = Modifier.padding(it),
                    navController = navController
                )
            }
        }
    }
}