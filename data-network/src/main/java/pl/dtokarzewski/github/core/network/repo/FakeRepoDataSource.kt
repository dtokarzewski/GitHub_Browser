package pl.dtokarzewski.github.core.network.repo

import pl.dtokarzewski.github.core.network.repo.model.OwnerApiModel
import pl.dtokarzewski.github.core.network.repo.model.RepoApiModel
import javax.inject.Inject

class FakeRepoDataSource @Inject constructor(): RepoDataSource {

    companion object {
        val fakeRepos = listOf(
            RepoApiModel(
                id = 1296269,
                name = "Hello-World",
                fullName = "octocat/Hello-World",
                description =  "This your first repo!",
                owner = OwnerApiModel(login = "octocat", url = "https://api.github.com/users/octocat"),
                stars = 80
            ),
            RepoApiModel(
                id = 4234532,
                name = "Hell0-Developer",
                fullName = "octocat/Hello-Developer",
                description =  "This your second repo!",
                owner = OwnerApiModel(login = "octocat", url = "https://api.github.com/users/octocat"),
                stars = 10
            ),
        )
    }

    override suspend fun getRepos(login: String): List<RepoApiModel> = fakeRepos

    override suspend fun getRepo(owner: String, name: String): RepoApiModel = fakeRepos.first()
}