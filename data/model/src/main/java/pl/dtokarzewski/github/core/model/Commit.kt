package pl.dtokarzewski.github.core.model

data class Commit(
    val commit: CommitDetails,
    val sha: String,
    val htmlUrl: String,
)
