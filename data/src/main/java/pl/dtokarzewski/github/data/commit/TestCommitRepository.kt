package pl.dtokarzewski.github.data.commit

import androidx.paging.PagingData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import pl.dtokarzewski.github.core.model.Commit
import pl.dtokarzewski.github.core.model.Repo

class TestCommitRepository() : CommitRepository {

    private val commitsFlow = MutableSharedFlow<PagingData<Commit>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override fun getCommits(repo: Repo): Flow<PagingData<Commit>> = commitsFlow

    fun setCommits(commits: List<Commit>) {
        commitsFlow.tryEmit(PagingData.from(commits))
    }
}