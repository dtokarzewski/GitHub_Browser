package pl.dtokarzewski.github.core.network.commit

import pl.dtokarzewski.github.core.network.commit.model.CommitApiModel
import pl.dtokarzewski.github.core.network.commit.model.CommitDetailsApiModel
import pl.dtokarzewski.github.core.network.commit.model.ContributorApiModel
import pl.dtokarzewski.github.core.network.commit.model.TreeApiModel
import javax.inject.Inject

class FakeCommitDataSource @Inject constructor(): CommitDataSource {

    companion object {
        val fakeAuthor = ContributorApiModel(
            name = "Monalisa Octocat",
            email = "support@github.com",
            date = "2011-04-14T16:00:49Z\""
        )
        val fakeCommits = listOf(
            CommitApiModel(
                url = "https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e",
                sha = "6dcb09b5b57875f334f61aebed695e2e4193db5e",
                htmlUrl = "https://github.com/octocat/Hello-World/commit/6dcb09b5b57875f334f61aebed695e2e4193db5e",
                commentsUrl = "https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e/comments",
                commit = CommitDetailsApiModel(
                    author = fakeAuthor,
                    commentCount = 0,
                    committer = fakeAuthor,
                    message = "Fix all the bugs",
                    tree = TreeApiModel(
                        sha = "https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e",
                        url = "https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e"
                    ),
                    url = "https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e"
                ),
                nodeId = "MDY6Q29tbWl0NmRjYjA5YjViNTc4NzVmMzM0ZjYxYWViZWQ2OTVlMmU0MTkzZGI1ZQ=="
            )
        )
    }

    override suspend fun getCommits(owner: String, name: String, page: Int): List<CommitApiModel> = fakeCommits
}