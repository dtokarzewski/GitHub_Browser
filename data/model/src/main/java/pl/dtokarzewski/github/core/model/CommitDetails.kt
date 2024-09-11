package pl.dtokarzewski.github.core.model

data class CommitDetails(
    val author: Contributor,
    val commentCount: Int,
    val committer: Contributor,
    val message: String,
)
