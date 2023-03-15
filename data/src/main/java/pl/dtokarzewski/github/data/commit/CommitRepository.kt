package pl.dtokarzewski.github.data.commit

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.dtokarzewski.github.core.model.Commit
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.network.commit.CommitDataSource
import pl.dtokarzewski.github.data.commit.mapper.mapToCommit
import pl.dtokarzewski.github.data.db.dao.CommitDao
import javax.inject.Inject

class CommitRepository @Inject constructor(
    private val commitDao: CommitDao,
    private val commitDataSource: CommitDataSource
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getCommits(repo: Repo): Flow<PagingData<Commit>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 40
            ),
            remoteMediator = CommitMediator(repo, commitDao, commitDataSource),
            pagingSourceFactory = { commitDao.getPagedCommits(repo.id) }
        ).flow.map { it.map { dbCommit -> dbCommit.mapToCommit() } }
    }
}