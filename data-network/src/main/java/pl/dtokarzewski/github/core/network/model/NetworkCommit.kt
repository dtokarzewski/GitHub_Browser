package pl.dtokarzewski.github.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCommit(
    @SerialName("comments_url")
    val commentsUrl: String,
    @SerialName("commit")
    val commit: CommitDetails,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("sha")
    val sha: String,
    @SerialName("url")
    val url: String
) {
    @Serializable
    data class CommitDetails(
        @SerialName("author")
        val author: Contributor,
        @SerialName("comment_count")
        val commentCount: Int,
        @SerialName("committer")
        val committer: Contributor,
        @SerialName("message")
        val message: String,
        @SerialName("tree")
        val tree: Tree,
        @SerialName("url")
        val url: String,
    )

    @Serializable
    data class Contributor(
        @SerialName("date")
        val date: String,
        @SerialName("email")
        val email: String,
        @SerialName("name")
        val name: String
    )

    @Serializable
    data class Tree(
        @SerialName("sha")
        val sha: String,
        @SerialName("url")
        val url: String
    )
}