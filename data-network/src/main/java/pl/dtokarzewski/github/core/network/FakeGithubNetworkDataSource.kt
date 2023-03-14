package pl.dtokarzewski.github.core.network

import pl.dtokarzewski.github.core.network.model.NetworkRepo
import javax.inject.Inject

class FakeGithubNetworkDataSource @Inject constructor(): GithubNetworkDataSource {

    companion object {
        val fakeRepos = listOf(
            NetworkRepo(
                id = 1296269,
                name = "Hello-World",
                fullName = "octocat/Hello-World",
                description =  "This your first repo!",
                owner = NetworkRepo.Owner(login = "octocat", url = "https://api.github.com/users/octocat"),
                stars = 80
            ),
            NetworkRepo(
                id = 4234532,
                name = "Hell0-Developer",
                fullName = "octocat/Hello-Developer",
                description =  "This your second repo!",
                owner = NetworkRepo.Owner(login = "octocat", url = "https://api.github.com/users/octocat"),
                stars = 10
            ),
        )
    }

    override suspend fun getRepos(login: String): List<NetworkRepo> = fakeRepos

    override suspend fun getRepo(owner: String, name: String): NetworkRepo = fakeRepos.first()
}