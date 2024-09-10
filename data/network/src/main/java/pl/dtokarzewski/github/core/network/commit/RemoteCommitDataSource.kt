package pl.dtokarzewski.github.core.network.commit

import pl.dtokarzewski.github.core.network.GithubService
import pl.dtokarzewski.github.core.network.commit.model.CommitApiModel
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteCommitDataSource @Inject constructor(
    retrofit: Retrofit
) : CommitDataSource {

    private val githubService = retrofit.create(GithubService::class.java)

    override suspend fun getCommits(
        owner: String,
        name: String,
        page: Int
    ): List<CommitApiModel> = githubService.getCommits(owner, name, page)
}