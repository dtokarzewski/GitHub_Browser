package pl.dtokarzewski.github.core.network.repo

import pl.dtokarzewski.github.core.network.GithubService
import pl.dtokarzewski.github.core.network.repo.model.RepoApiModel
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteRepoDataSource @Inject constructor(
    retrofit: Retrofit
) : RepoDataSource {

    private val githubService = retrofit.create(GithubService::class.java)

    override suspend fun getRepos(login: String): List<RepoApiModel> =
        githubService.getRepos(login)

    override suspend fun getRepo(owner: String, name: String): RepoApiModel =
        githubService.getRepo(owner, name)
}