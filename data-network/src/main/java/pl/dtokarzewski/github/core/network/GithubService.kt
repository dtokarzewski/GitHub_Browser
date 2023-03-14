package pl.dtokarzewski.github.core.network

import pl.dtokarzewski.github.core.network.model.NetworkRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{login}/repos")
    suspend fun getRepos(@Path("login") login: String): List<NetworkRepo>

    @GET("repos/{owner}/{name}")
    suspend fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): NetworkRepo
}