package pl.dtokarzewski.github.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.network.repo.RemoteRepoDataSource
import pl.dtokarzewski.github.data.db.dao.RepoDao
import pl.dtokarzewski.github.data.repo.mapper.mapToRepo
import pl.dtokarzewski.github.data.repo.mapper.mapToRepoDbModel
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoDao: RepoDao,
    private val network: RemoteRepoDataSource
) : RepoRepository {

    override suspend fun getRepo(owner: String, name: String): Result<Repo> {
        // Prefer remote
        return runCatching {
            network.getRepo(owner, name)
        }.map { it.mapToRepo() }
            .onSuccess {
                updateDatabaseWithRepo(it)
            }.recoverCatching {
                if (it is HttpException && it.code() == 404) {
                    // Repo doesn't exist on remote. Might have been deleted. Make sure it's not in a
                    // Db either.
                    deleteRepoFromDb(owner, name)
                    throw it
                } else {
                    getRepoFromDb(owner, name)
                }
            }
    }

    override fun getRepoAsFlow(owner: String, name: String) = repoDao
        .getRepoAsFlow(owner, name)
        .map { it.mapToRepo() }

    override fun getAllRepos(): Flow<List<Repo>> = repoDao
        .getAllRepos()
        .map { repos -> repos.map { it.mapToRepo() } }

    private suspend fun updateDatabaseWithRepo(repo: Repo) {
        Timber.d("Saving repo ${repo.owner}/${repo.name} in Db")
        val dbRepo = repo.mapToRepoDbModel()
        repoDao.insert(dbRepo)
    }

    private suspend fun getRepoFromDb(owner: String, name: String): Repo {
        Timber.d("Failed to get repo $owner/$name from GitHub. Trying to get from local Db")
        val dbRepo = repoDao.getRepo(owner, name)
        return dbRepo.mapToRepo()
    }

    private suspend fun deleteRepoFromDb(owner: String, name: String) {
        Timber.d("Repo $owner/$name not found in GitHub. Removing from local Db")
        val count = repoDao.deleteRepo(owner, name)
        Timber.d(if (count != 0) "Repo removal success" else "Nothing to remove")
    }
}