package pl.dtokarzewski.github.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.dtokarzewski.github.domain.GetAllReposUseCase
import pl.dtokarzewski.github.domain.GetRepoUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRepoUseCase: GetRepoUseCase,
    private val getAllReposUseCase: GetAllReposUseCase
) : ViewModel() {

    private val repoNameState = MutableStateFlow("dtokarzewski/GitHub_Browser")
    private val loading = MutableStateFlow(false)

    val uiState: StateFlow<SearchUiState> = combine(
        repoNameState,
        getAllReposUseCase(),
        loading
    ) { repoName, allRepos, loading ->
        if (loading) {
            SearchUiState.Loading(repoName, allRepos)
        } else {
            SearchUiState.Success(repoName, allRepos)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState.Loading("dtokarzewski/GitHub_Browser", emptyList())
    )

    fun onRepoNameChanged(repoName: String) {
        repoNameState.value = repoName
    }

    fun onSearchClicked() {
        viewModelScope.launch {
            val owner = repoNameState.value.split("/")[0]
            val name = repoNameState.value.split("/")[1]
            val repo = getRepoUseCase(owner, name)
            Timber.d("Repo search result: $repo")
        }
    }
}