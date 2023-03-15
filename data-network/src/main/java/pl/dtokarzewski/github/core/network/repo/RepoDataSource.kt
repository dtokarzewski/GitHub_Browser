package pl.dtokarzewski.github.core.network.repo

import pl.dtokarzewski.github.core.network.repo.model.RepoApiModel

interface RepoDataSource {
    suspend fun getRepos(login: String): List<RepoApiModel>

    suspend fun getRepo(owner: String, name: String): RepoApiModel
}