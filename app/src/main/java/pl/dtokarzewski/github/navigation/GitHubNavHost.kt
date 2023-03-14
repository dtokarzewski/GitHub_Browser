package pl.dtokarzewski.github.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import pl.dtokarzewski.github.feature.repo.navigation.navigateToRepo
import pl.dtokarzewski.github.feature.repo.navigation.repoGraph
import pl.dtokarzewski.github.search.navigation.SEARCH_ROUTE
import pl.dtokarzewski.github.search.navigation.searchGraph

@Composable
fun GitHubNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = SEARCH_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        searchGraph(
            navigateToRepo = { owner, repoName -> navController.navigateToRepo(owner, repoName) }
        )
        repoGraph(
            navController = navController
        )
    }
}