package pl.dtokarzewski.github.search.ui

import androidx.compose.runtime.Immutable
import pl.dtokarzewski.github.core.model.Repo

sealed class SearchUiState {
    abstract val query: String
    abstract val isQueryValid: Boolean
    abstract val allRepos: List<Repo>

    @Immutable
    data class Loading(
        override val query: String,
        override val isQueryValid: Boolean,
        override val allRepos: List<Repo>
    ) : SearchUiState()

    @Immutable
    data class Idle(
        override val query: String,
        override val isQueryValid: Boolean,
        override val allRepos: List<Repo>
    ) : SearchUiState()

    @Immutable
    data class Error(
        val error: Throwable,
        override val query: String,
        override val isQueryValid: Boolean,
        override val allRepos: List<Repo>
    ) : SearchUiState()

    @Immutable
    data class NavigateToRepo(
        val owner: String,
        val name: String,
        override val query: String,
        override val isQueryValid: Boolean,
        override val allRepos: List<Repo>
    ) : SearchUiState()
}