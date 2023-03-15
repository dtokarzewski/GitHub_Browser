package pl.dtokarzewski.github.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pl.dtokarzewski.github.core.common.network.IoDispatcher
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.network.FakeGithubNetworkDataSource
import pl.dtokarzewski.github.data.mapper.mapToRepo
import javax.inject.Inject

class FakeRepoRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val fakeNetwork: FakeGithubNetworkDataSource
) : RepoRepository {

    override suspend fun getRepo(owner: String, name: String): Result<Repo> = Result.success(
        fakeNetwork
            .getRepo(owner, name)
            .mapToRepo()
    )

    override fun getRepoAsFlow(owner: String, name: String): Flow<Repo> =
        flow {
            emit(
                fakeNetwork.getRepo(owner, name).mapToRepo()
            )
        }.flowOn(dispatcher)

    override fun getAllRepos(): Flow<List<Repo>> =
        flow {
            emit(
                fakeNetwork.getRepos("").map { it.mapToRepo() }
            )
        }.flowOn(dispatcher)
}