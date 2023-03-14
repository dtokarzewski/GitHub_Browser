package pl.dtokarzewski.github.search.ui

import pl.dtokarzewski.github.core.model.Repo

sealed class SearchUiState {
    abstract val query: String
    abstract val allRepos: List<Repo>

    data class Loading(
        override val query: String,
        override val allRepos: List<Repo>
    ) : SearchUiState()

    data class Idle(
        override val query: String,
        override val allRepos: List<Repo>
    ) : SearchUiState()

    data class Error(
        val error: Throwable,
        override val query: String,
        override val allRepos: List<Repo>
    ) : SearchUiState()

    data class NavigateToRepo(
        val owner: String,
        val name: String,
        override val query: String,
        override val allRepos: List<Repo>
    ) : SearchUiState()
}