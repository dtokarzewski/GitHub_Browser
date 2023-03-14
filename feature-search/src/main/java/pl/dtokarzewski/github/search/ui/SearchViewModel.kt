package pl.dtokarzewski.github.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.dtokarzewski.github.core.common.util.logViewState
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.domain.GetAllReposUseCase
import pl.dtokarzewski.github.domain.GetRepoUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRepoUseCase: GetRepoUseCase,
    getAllReposUseCase: GetAllReposUseCase
) : ViewModel() {

    private val query = MutableStateFlow("dtokarzewski/GitHub_Browser")
    private val loading = MutableStateFlow(false)
    private val selectedRepo = MutableStateFlow<Repo?>(null)
    private val error = MutableStateFlow<Throwable?>(null)

    val uiState: StateFlow<SearchUiState> = combine(
        query,
        getAllReposUseCase(),
        selectedRepo,
        loading,
        error
    ) { query, allRepos, repo, loading, error ->
        if (error != null) {
            SearchUiState.Error(error, query, allRepos)
        }
        if (repo != null) {
            SearchUiState.NavigateToRepo(repo.owner.login, repo.name, query, allRepos)
        }
        if (loading) {
            SearchUiState.Loading(query, allRepos)
        } else {
            SearchUiState.Idle(query, allRepos)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState.Loading("dtokarzewski/GitHub_Browser", emptyList())
    )

    init {
        logViewState(uiState)
    }

    fun onQueryChanged(newQuery: String) {
        query.value = newQuery
    }

    fun onSearchClicked() {
        loading.value = true
        viewModelScope.launch {
            val owner = query.value.split("/")[0]
            val name = query.value.split("/")[1]
            getRepoUseCase(owner, name)
                .onSuccess {
                    selectedRepo.value = it
                    loading.value = false
                }
                .onFailure {
                    error.value = it
                    loading.value = false
                }
        }
    }

    fun onNavigated() {
        selectedRepo.value = null
    }

    fun onErrorShow() {
        error.value = null
    }

    internal sealed interface QueryState {
        object Idle : QueryState
        object Loading : QueryState
        class Error(error: Throwable) : QueryState
        class Success(repo: Repo) : QueryState
    }
}