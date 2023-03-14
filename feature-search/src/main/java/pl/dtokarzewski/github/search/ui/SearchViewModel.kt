package pl.dtokarzewski.github.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.dtokarzewski.github.domain.GetRepoUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRepoUseCase: GetRepoUseCase
) : ViewModel() {

    private val repoNameState = MutableStateFlow("dtokarzewski/GitHub_Browser")
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
        initialValue = SearchUiState.Loading("dtokarzewski/GitHub_Browser")
    )

    fun onRepoNameChanged(repoName: String) {
        repoNameState.value = repoName
    }

    fun onSearchClicked() {
        try {
            viewModelScope.launch {
                val owner = repoNameState.value.split("/")[0]
                val name = repoNameState.value.split("/")[1]
                val repo = getRepoUseCase(owner, name)
                Timber.d("Repo search result: $repo")
            }
        } catch (error: Throwable) {
            Timber.e(error)
        }
    }
}