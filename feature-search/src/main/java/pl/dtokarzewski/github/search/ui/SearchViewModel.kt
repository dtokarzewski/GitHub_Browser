package pl.dtokarzewski.github.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.dtokarzewski.github.core.common.util.logViewState
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.domain.repo.GetAllReposUseCase
import pl.dtokarzewski.github.domain.repo.GetRepoUseCase
import pl.dtokarzewski.github.domain.repo.ValidateRepoNameUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRepoUseCase: GetRepoUseCase,
    private val validateRepoNameUseCase: ValidateRepoNameUseCase,
    getAllReposUseCase: GetAllReposUseCase
) : ViewModel() {

    private val query = MutableStateFlow("")
    private val queryValid = MutableStateFlow(false)
    private val queryState = MutableStateFlow<QueryState>(QueryState.Idle)

    val uiState: StateFlow<SearchUiState> = combine(
        query,
        queryValid,
        queryState,
        getAllReposUseCase(),
    ) { query, queryValid, queryState, allRepos ->
        when (queryState) {
            is QueryState.Error -> SearchUiState.Error(
                error = queryState.error,
                query = query,
                isQueryValid = queryValid,
                allRepos = allRepos
            )
            is QueryState.Success -> SearchUiState.NavigateToRepo(
                owner = queryState.repo.owner.login,
                name = queryState.repo.name,
                query = query,
                isQueryValid = queryValid,
                allRepos = allRepos
            )
            is QueryState.Loading -> SearchUiState.Loading(query, queryValid, allRepos)
            is QueryState.Idle -> SearchUiState.Idle(query, queryValid, allRepos)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState.Idle("", false, emptyList())
    )

    init {
        logViewState(uiState)
    }

    fun onQueryChanged(newQuery: String) {
        query.value = newQuery
        queryValid.value = validateRepoNameUseCase(newQuery)
    }

    fun onSearchClicked() {
        queryState.value = QueryState.Loading
        val owner = query.value.split("/")[0]
        val name = query.value.split("/")[1]
        openRepo(owner, name)
    }

    fun onRecentRepoClicked(repo: Repo) {
        queryState.value = QueryState.Loading
        query.value = "${repo.owner.login}/${repo.name}"
        queryValid.value = validateRepoNameUseCase(query.value)
        openRepo(repo.owner.login, repo.name)
    }

    private fun openRepo(owner: String, name: String) {
        viewModelScope.launch {
            getRepoUseCase(owner, name)
                .onSuccess {
                    queryState.value = QueryState.Success(it)
                }
                .onFailure {
                    queryState.value = QueryState.Error(it)
                }
        }
    }

    fun onNavigated() {
        queryState.value = QueryState.Idle
    }

    fun onErrorShown() {
        queryState.value = QueryState.Idle
    }

    internal sealed interface QueryState {
        object Idle : QueryState
        object Loading : QueryState
        data class Error(val error: Throwable) : QueryState
        data class Success(val repo: Repo) : QueryState
    }
}