package pl.dtokarzewski.github.core.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.res.stringResource

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error("No SnackbarHostState provided")
}

@Composable
fun ErrorSnackbar(
    snackbarHostState: SnackbarHostState,
    error: Throwable?, onErrorShown: () -> Unit
) {
    val errorMessage = error?.message ?: stringResource(id = R.string.error_message_general)
    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar(errorMessage)
        onErrorShown()
    }
}