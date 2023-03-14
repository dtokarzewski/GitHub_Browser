package pl.dtokarzewski.github.core.network

import kotlinx.coroutines.CoroutineDispatcher
import pl.dtokarzewski.github.core.common.network.IoDispatcher
import pl.dtokarzewski.github.core.network.model.NetworkRepo
import javax.inject.Inject

class FakeGithubNetworkDataSource @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
): GithubNetworkDataSource {

    companion object {
    }
    override suspend fun getRepos(login: String): List<NetworkRepo> {
        TODO("Not yet implemented")
    }

    override suspend fun getRepo(owner: String, name: String): NetworkRepo {
        TODO("Not yet implemented")
    }
}