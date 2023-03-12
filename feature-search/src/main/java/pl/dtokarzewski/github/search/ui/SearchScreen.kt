package pl.dtokarzewski.github.search.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
) {
    SearchScreen()
}

@Composable
fun SearchScreen() {

}