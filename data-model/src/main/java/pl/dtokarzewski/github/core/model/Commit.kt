package pl.dtokarzewski.github.core.model

data class Commit(
    val commit: CommitDetails,
    val sha: String,
    val htmlUrl: String,
) {
    data class CommitDetails(
        val author: Contributor,
        val commentCount: Int,
        val committer: Contributor,
        val message: String,
    )

    data class Contributor(
        val date: String,
        val email: String,
        val name: String
    )
}