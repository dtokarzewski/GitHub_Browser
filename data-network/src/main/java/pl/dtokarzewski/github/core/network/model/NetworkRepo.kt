package pl.dtokarzewski.github.core.network.model

import kotlinx.serialization.SerialName

data class NetworkRepo(
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("description")
    val description: String?,
    @SerialName("owner")
    val owner: Owner,
    @SerialName("stargazers_count")
    val stars: Int
) {

    data class Owner(
        @SerialName("login")
        val login: String,
        @SerialName("url")
        val url: String?
    )
}
