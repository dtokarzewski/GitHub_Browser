package pl.dtokarzewski.github.core.network

import pl.dtokarzewski.github.core.network.model.NetworkRepo
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitGithubDataSource @Inject constructor(
    retrofit: Retrofit
) : GithubNetworkDataSource {

    private val githubService = retrofit.create(GithubService::class.java)

    override suspend fun getRepos(login: String): List<NetworkRepo> =
        githubService.getRepos(login)

    override suspend fun getRepo(owner: String, name: String): NetworkRepo =
        githubService.getRepo(owner, name)
}