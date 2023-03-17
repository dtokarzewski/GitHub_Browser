package pl.dtokarzewski.github.data.commit

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pl.dtokarzewski.github.core.model.Commit
import pl.dtokarzewski.github.core.model.Repo

interface CommitRepository  {

    fun getCommits(repo: Repo): Flow<PagingData<Commit>>
}