package pl.dtokarzewski.github.data.repository

import kotlinx.coroutines.flow.Flow
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.network.RetrofitGithubDataSource
import pl.dtokarzewski.github.data.db.RepoDao
import pl.dtokarzewski.github.data.mapper.DbRepoToRepoMapper
import pl.dtokarzewski.github.data.mapper.NetworkRepoToRepoMapper
import pl.dtokarzewski.github.data.mapper.RepoToDbRepoMapper
import retrofit2.HttpException
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoDao: RepoDao,
    private val network: RetrofitGithubDataSource
) : RepoRepository {

    override suspend fun getRepo(owner: String, name: String): Result<Repo> {
        // Prefer remote
        return runCatching {
            network.getRepo(owner, name)
        }.map { NetworkRepoToRepoMapper.map(it) }
            .onSuccess {
                // Add or update repo in Db
                updateDatabaseWithRepo(it)
            }.recoverCatching {
                // Repo doesn't exist on remote. Might have been deleted. Make sure it's not in a
                // Db either.
                if (it is HttpException && it.code() == 404) {
                    repoDao.deleteRepo(owner, name)
                    throw it
                } else {
                    getRepoFromDb(owner, name)
                }

            }

    }

    override suspend fun getAllRepos(): Flow<Repo> {
        TODO("Not yet implemented")
    }

    private suspend fun updateDatabaseWithRepo(repo: Repo) {
        val dbRepo = repo
            .let { RepoToDbRepoMapper.map(it) }
        repoDao.insert(dbRepo)
    }

    private suspend fun getRepoFromDb(owner: String, name: String): Repo {
        val dbRepo = repoDao.getRepo(owner, name)
        return DbRepoToRepoMapper.map(dbRepo)
    }
}