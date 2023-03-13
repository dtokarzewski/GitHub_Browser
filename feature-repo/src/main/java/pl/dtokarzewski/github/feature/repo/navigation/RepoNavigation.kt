package pl.dtokarzewski.github.feature.repo.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.composable
import pl.dtokarzewski.github.feature.repo.ui.RepoRoute

const val REPO_ROUTE = "repo"
const val ARG_REPO_NAME = "repoName"

fun NavController.navigateToRepo(repoName: String, navOptions: NavOptions? = null) {
    val encodedRepoName = Uri.encode(repoName)
    this.navigate("$REPO_ROUTE/$encodedRepoName", navOptions)
}

fun NavGraphBuilder.repoGraph(
    navController: NavController
) {
    composable(
        route = "$REPO_ROUTE/{$ARG_REPO_NAME}",
        arguments = listOf(
            navArgument(ARG_REPO_NAME) { type = NavType.StringType }
        )
    ) {
        RepoRoute(
            navigateUp = { navController.popBackStack() }
        )
    }
}

// Repo name contains "/" which conflicts with navigation route path, so name needs to be encoded.
internal class RepoArgs(val repoName: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(Uri.decode(checkNotNull(savedStateHandle[ARG_REPO_NAME])))
}