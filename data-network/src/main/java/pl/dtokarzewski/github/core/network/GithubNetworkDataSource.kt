package pl.dtokarzewski.github.core.network

import pl.dtokarzewski.github.core.network.model.NetworkRepo

interface GithubNetworkDataSource {
    suspend fun getRepos(login: String): List<NetworkRepo>

    suspend fun getRepo(owner: String, name: String): NetworkRepo
}