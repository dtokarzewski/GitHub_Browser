package pl.dtokarzewski.github.data.commit

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.network.commit.CommitDataSource
import pl.dtokarzewski.github.data.commit.mapper.toDomainModel
import pl.dtokarzewski.github.data.commit.mapper.toDbModel
import pl.dtokarzewski.github.data.db.dao.CommitDao
import pl.dtokarzewski.github.data.db.model.CommitDbModel
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CommitMediator(
    private val repo: Repo,
    private val commitDao: CommitDao,
    private val commitDataSource: CommitDataSource
) : RemoteMediator<Int, CommitDbModel>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CommitDbModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    state.pages.count() + 1
                }
            }

            val commits = commitDataSource.getCommits(repo.owner.login, repo.name, loadKey)

            commitDao.insertAll(commits.map { it.toDomainModel().toDbModel(repo.id) })

            MediatorResult.Success(
                endOfPaginationReached = commits.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}