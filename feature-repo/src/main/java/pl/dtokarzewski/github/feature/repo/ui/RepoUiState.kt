package pl.dtokarzewski.github.feature.repo.ui

sealed class RepoUiState {
    abstract val repoName: String

    data class Loading(
        override val repoName: String
    ) : RepoUiState()

    data class Success(
        override val repoName: String
    ) : RepoUiState()

    data class Error(
        val error: Throwable,
        override val repoName: String
    ) : RepoUiState()
}