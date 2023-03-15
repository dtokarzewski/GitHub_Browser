package pl.dtokarzewski.github.data.commit.mapper

import pl.dtokarzewski.github.core.model.Commit
import pl.dtokarzewski.github.core.network.commit.model.CommitApiModel
import pl.dtokarzewski.github.data.db.model.CommitDbModel

fun CommitApiModel.mapToCommit() = Commit(
    sha = this.sha,
    htmlUrl = this.htmlUrl,
    commit = this.commit.mapToCommitDetails()
)

fun Commit.mapToDbCommit(repoId: Int) = CommitDbModel(
    sha = this.sha,
    htmlUrl = this.htmlUrl,
    repoId = repoId,
    commit = this.commit.mapToCommitDetailsDbModel()
)

fun CommitDbModel.mapToCommit() = Commit(
    sha = this.sha,
    htmlUrl = this.htmlUrl,
    commit = this.commit.mapToCommitDetails()
)