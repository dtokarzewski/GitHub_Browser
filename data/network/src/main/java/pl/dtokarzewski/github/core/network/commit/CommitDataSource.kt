package pl.dtokarzewski.github.core.network.commit

import pl.dtokarzewski.github.core.network.commit.model.CommitApiModel

interface CommitDataSource {

    suspend fun getCommits(owner: String, name: String, page: Int): List<CommitApiModel>
}