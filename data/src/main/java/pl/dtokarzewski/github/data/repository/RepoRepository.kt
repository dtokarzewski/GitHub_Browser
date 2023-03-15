package pl.dtokarzewski.github.data.repository

import kotlinx.coroutines.flow.Flow
import pl.dtokarzewski.github.core.model.Repo

interface RepoRepository {

    suspend fun getRepo(owner: String, name: String): Result<Repo>

    fun getRepoAsFlow(owner: String, name: String): Flow<Repo>

    fun getAllRepos(): Flow<List<Repo>>
}