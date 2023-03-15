package pl.dtokarzewski.github.core.network.repo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoApiModel(
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("description")
    val description: String?,
    @SerialName("owner")
    val owner: OwnerApiModel,
    @SerialName("stargazers_count")
    val stars: Int
)

@Serializable
data class OwnerApiModel(
    @SerialName("login")
    val login: String,
    @SerialName("url")
    val url: String?
)
