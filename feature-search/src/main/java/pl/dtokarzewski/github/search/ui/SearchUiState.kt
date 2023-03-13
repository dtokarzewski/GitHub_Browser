package pl.dtokarzewski.github.search.ui

sealed class SearchUiState {
    abstract val repoName: String

    data class Loading(
        override val repoName: String
    ) : SearchUiState()

    data class Success(
        override val repoName: String
    ) : SearchUiState()

    data class Error(
        val error: Throwable,
        override val repoName: String
    ) : SearchUiState()
}