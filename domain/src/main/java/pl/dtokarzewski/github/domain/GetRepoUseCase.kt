package pl.dtokarzewski.github.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import pl.dtokarzewski.github.core.common.network.IoDispatcher
import pl.dtokarzewski.github.data.repository.RepoRepository
import javax.inject.Inject

class GetRepoUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    // TODO map errors
    suspend operator fun invoke(owner: String, name: String) = withContext(dispatcher) {
        repoRepository.getRepo(owner, name)
    }
}