package pl.dtokarzewski.github.data.repository

import kotlinx.coroutines.flow.Flow
import pl.dtokarzewski.github.core.model.Repo

interface RepoRepository {

    suspend fun getRepo(owner: String, name: String): Result<Repo>

    suspend fun getAllRepos(): Flow<Repo>
}