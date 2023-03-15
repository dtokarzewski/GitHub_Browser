package pl.dtokarzewski.github.feature.repo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import pl.dtokarzewski.github.core.common.util.logViewState
import pl.dtokarzewski.github.domain.commit.GetCommitUseCase
import pl.dtokarzewski.github.domain.repo.GetRepoAsFlowUseCse
import pl.dtokarzewski.github.feature.repo.navigation.RepoArgs
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getRepoAsFlowUseCase: GetRepoAsFlowUseCse,
    getCommitUseCase: GetCommitUseCase
) : ViewModel() {

    private val repoArgs = RepoArgs(savedStateHandle)
    private val owner: String = repoArgs.owner
    private val repoName: String = repoArgs.repoName

    val commits = getCommitUseCase(owner, repoName).cachedIn(viewModelScope)

    val uiState: StateFlow<RepoUiState> =
        getRepoAsFlowUseCase(owner, repoName).map { repo ->
            RepoUiState.Success(repo)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RepoUiState.Loading
        )

    init {
        logViewState(uiState)
    }
}