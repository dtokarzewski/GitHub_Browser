package pl.dtokarzewski.github.domain

import pl.dtokarzewski.github.data.repo.RepoRepository
import javax.inject.Inject

class GetRepoAsFlowUseCse @Inject constructor(
    private val repoRepository: RepoRepository
) {

    operator fun invoke(owner: String, name: String) = repoRepository.getRepoAsFlow(owner, name)
}