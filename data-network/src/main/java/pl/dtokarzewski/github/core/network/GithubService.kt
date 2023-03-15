package pl.dtokarzewski.github.core.network

import pl.dtokarzewski.github.core.network.commit.model.CommitApiModel
import pl.dtokarzewski.github.core.network.repo.model.RepoApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("users/{login}/repos")
    suspend fun getRepos(@Path("login") login: String): List<RepoApiModel>

    @GET("repos/{owner}/{name}")
    suspend fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): RepoApiModel

    @GET("repos/{owner}/{name}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("name") name: String,
        @Query("page") page: Int
    ): List<CommitApiModel>
}