package pl.dtokarzewski.github.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.dtokarzewski.github.feature.search.R

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
) {
    SearchScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .align(alignment = CenterHorizontally),
            value = "",
            label = { Text(text = stringResource(id = R.string.repository_name)) },
            onValueChange = {}
        )
    }
}