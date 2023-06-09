package pl.dtokarzewski.github.data.repo

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pl.dtokarzewski.github.core.model.Repo

class TestRepoRepository : RepoRepository {

    private val reposFlow = MutableSharedFlow<List<Repo>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private var repoResult = MutableSharedFlow<Result<Repo>>()

    override suspend fun getRepo(owner: String, name: String): Result<Repo> = repoResult.first()

    override fun getRepoAsFlow(owner: String, name: String): Flow<Repo> =
        reposFlow.map {
            repos -> repos.find { it.owner.login == owner && it.name == name }!!
    }

    override fun getAllRepos(): Flow<List<Repo>> = reposFlow

    fun setRepos(repos: List<Repo>) {
        reposFlow.tryEmit(repos)
    }

    suspend fun setRepoResult(repoResult: Result<Repo>) {
        this.repoResult.emit(repoResult)
    }
}