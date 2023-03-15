package pl.dtokarzewski.github.domain

import pl.dtokarzewski.github.data.repo.RepoRepository
import javax.inject.Inject

class GetAllReposUseCase@Inject constructor(
    private val repoRepository: RepoRepository
) {

    operator fun invoke() = repoRepository.getAllRepos()
}