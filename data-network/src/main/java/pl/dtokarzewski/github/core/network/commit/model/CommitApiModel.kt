package pl.dtokarzewski.github.core.network.commit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommitApiModel(
    @SerialName("comments_url")
    val commentsUrl: String,
    @SerialName("commit")
    val commit: CommitDetailsApiModel,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("sha")
    val sha: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class CommitDetailsApiModel(
    @SerialName("author")
    val author: ContributorApiModel,
    @SerialName("comment_count")
    val commentCount: Int,
    @SerialName("committer")
    val committer: ContributorApiModel,
    @SerialName("message")
    val message: String,
    @SerialName("tree")
    val tree: TreeApiModel,
    @SerialName("url")
    val url: String,
)

@Serializable
data class ContributorApiModel(
    @SerialName("date")
    val date: String,
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String
)

@Serializable
data class TreeApiModel(
    @SerialName("sha")
    val sha: String,
    @SerialName("url")
    val url: String
)
