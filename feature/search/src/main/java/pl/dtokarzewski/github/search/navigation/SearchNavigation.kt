package pl.dtokarzewski.github.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import pl.dtokarzewski.github.search.ui.SearchRoute

const val SEARCH_ROUTE = "search"

fun NavGraphBuilder.searchGraph(
    navigateToRepo: (String, String) -> Unit
) {
    composable(route = SEARCH_ROUTE) {
        SearchRoute(
            navigateToRepo = navigateToRepo
        )
    }
}