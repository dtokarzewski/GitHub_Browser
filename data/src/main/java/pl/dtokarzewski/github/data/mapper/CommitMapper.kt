package pl.dtokarzewski.github.data.mapper

import pl.dtokarzewski.github.core.model.Commit
import pl.dtokarzewski.github.core.network.model.NetworkCommit
import pl.dtokarzewski.github.data.db.model.DbCommit

fun NetworkCommit.mapToCommit() = Commit(
    sha = this.sha,
    htmlUrl = this.htmlUrl,
    commit = this.commit.mapToCommitDetails()
)

fun Commit.mapToDbCommit(repoId: Int) = DbCommit(
    sha = this.sha,
    htmlUrl = this.htmlUrl,
    repoId = repoId,
    commit = this.commit.mapToDbCommitDetails()
)

fun NetworkCommit.CommitDetails.mapToCommitDetails() = Commit.CommitDetails(
    message = this.message,
    author = this.author.mapToCommitContributor(),
    committer = this.committer.mapToCommitContributor(),
    commentCount = this.commentCount
)

fun Commit.CommitDetails.mapToDbCommitDetails() = DbCommit.CommitDetails(
    message = this.message,
    author = this.author.mapToDbCommitContributor(),
    committer = this.committer.mapToDbCommitContributor(),
    commentCount = this.commentCount
)

fun DbCommit.CommitDetails.mapToCommitDetails() = Commit.CommitDetails(
    message = this.message,
    author = this.author.mapToCommitContributor(),
    committer = this.committer.mapToCommitContributor(),
    commentCount = this.commentCount
)

fun NetworkCommit.Contributor.mapToCommitContributor() = Commit.Contributor(
    date = this.date,
    name = this.name,
    email = this.email
)

fun Commit.Contributor.mapToDbCommitContributor() = DbCommit.Contributor(
    date = this.date,
    name = this.name,
    email = this.email
)

fun DbCommit.Contributor.mapToCommitContributor() = Commit.Contributor(
    date = this.date,
    name = this.name,
    email = this.email
)