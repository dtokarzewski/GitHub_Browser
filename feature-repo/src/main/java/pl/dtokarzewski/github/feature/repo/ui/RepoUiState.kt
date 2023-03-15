package pl.dtokarzewski.github.feature.repo.ui

import pl.dtokarzewski.github.core.model.Repo

sealed class RepoUiState {

    object Loading : RepoUiState()

    data class Success(
        val repo: Repo
    ) : RepoUiState()

    data class Error(
        val error: Throwable,
        val repo: Repo
    ) : RepoUiState()
}