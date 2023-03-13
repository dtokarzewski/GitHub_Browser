package pl.dtokarzewski.github.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {

    private val repoNameState = MutableStateFlow("")
    private val loading = MutableStateFlow(false)

    val uiState: StateFlow<SearchUiState> = combine(
        repoNameState,
        loading
    ) { repoName, loading ->
        if (loading) {
            SearchUiState.Loading(repoName)
        } else {
            SearchUiState.Success(repoName)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState.Loading("")
    )

    fun onRepoNameChanged(repoName: String) {
        repoNameState.value = repoName
    }
}