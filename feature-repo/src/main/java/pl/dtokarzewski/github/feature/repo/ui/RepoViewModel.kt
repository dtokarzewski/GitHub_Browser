package pl.dtokarzewski.github.feature.repo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import pl.dtokarzewski.github.core.common.util.logViewState
import pl.dtokarzewski.github.domain.GetRepoAsFlowUseCse
import pl.dtokarzewski.github.feature.repo.navigation.RepoArgs
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getRepoAsFlowUseCse: GetRepoAsFlowUseCse,
) : ViewModel() {

    private val repoArgs = RepoArgs(savedStateHandle)
    private val owner: String = repoArgs.owner
    private val repoName: String = repoArgs.repoName

    val uiState: StateFlow<RepoUiState> =
        getRepoAsFlowUseCse(owner, repoName).map { repo ->
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