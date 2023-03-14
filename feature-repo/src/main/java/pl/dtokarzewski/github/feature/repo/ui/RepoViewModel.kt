package pl.dtokarzewski.github.feature.repo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.dtokarzewski.github.core.common.util.logViewState
import pl.dtokarzewski.github.feature.repo.navigation.RepoArgs
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val repoArgs = RepoArgs(savedStateHandle)
    private val owner: String = repoArgs.owner
    private val repoName: String = repoArgs.repoName

    val uiState: StateFlow<RepoUiState> = MutableStateFlow(RepoUiState.Success(repoName))

    init {
        logViewState(uiState)
    }
}