package pl.dtokarzewski.github.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import pl.dtokarzewski.github.search.ui.SearchRoute

const val SEARCH_ROUTE = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(SEARCH_ROUTE, navOptions)
}

fun NavGraphBuilder.searchGraph() {
    composable(route = SEARCH_ROUTE) {
        SearchRoute()
    }
}