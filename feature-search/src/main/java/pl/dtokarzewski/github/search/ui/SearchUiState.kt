package pl.dtokarzewski.github.search.ui

import pl.dtokarzewski.github.core.model.Repo

sealed class SearchUiState {
    abstract val repoName: String
    abstract val allRepos: List<Repo>

    data class Loading(
        override val repoName: String,
        override val allRepos: List<Repo>
    ) : SearchUiState()

    data class Success(
        override val repoName: String,
        override val allRepos: List<Repo>
    ) : SearchUiState()

    data class Error(
        val error: Throwable,
        override val repoName: String,
        override val allRepos: List<Repo>
    ) : SearchUiState()
}