package pl.dtokarzewski.github.feature.repo.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.composable
import pl.dtokarzewski.github.feature.repo.ui.RepoRoute

const val REPO_ROUTE = "repo"
const val ARG_OWNER = "owner"
const val ARG_REPO_NAME = "repoName"

fun NavController.navigateToRepo(owner: String, repoName: String, navOptions: NavOptions? = null) {
    this.navigate("$REPO_ROUTE/$owner/$repoName", navOptions)
}

fun NavGraphBuilder.repoGraph(
    navController: NavController
) {
    composable(
        route = "$REPO_ROUTE/{$ARG_OWNER}/{$ARG_REPO_NAME}",
        arguments = listOf(
            navArgument(ARG_REPO_NAME) { type = NavType.StringType }
        )
    ) {
        RepoRoute(
            navigateUp = { navController.popBackStack() }
        )
    }
}

internal class RepoArgs(val owner: String, val repoName: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[ARG_OWNER]), checkNotNull(savedStateHandle[ARG_REPO_NAME]))
}