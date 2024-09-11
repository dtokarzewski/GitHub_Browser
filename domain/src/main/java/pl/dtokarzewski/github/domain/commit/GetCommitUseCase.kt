package pl.dtokarzewski.github.domain.commit

import androidx.paging.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import pl.dtokarzewski.github.core.model.Commit
import pl.dtokarzewski.github.data.commit.CommitRepository
import pl.dtokarzewski.github.data.repo.RepoRepository
import javax.inject.Inject

class GetCommitUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
    private val commitRepository: CommitRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(owner: String, name: String): Flow<PagingData<Commit>> =
        repoRepository
            .getRepoAsFlow(owner, name)
            .flatMapMerge { commitRepository.getCommits(it) }
}