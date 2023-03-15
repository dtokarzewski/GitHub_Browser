package pl.dtokarzewski.github.data.commit.mapper

import pl.dtokarzewski.github.core.model.CommitDetails
import pl.dtokarzewski.github.core.network.commit.model.CommitDetailsApiModel
import pl.dtokarzewski.github.data.db.model.CommitDetailsDbModel

fun CommitDetailsApiModel.mapToCommitDetails() = CommitDetails(
    message = this.message,
    author = this.author.mapToContributor(),
    committer = this.committer.mapToContributor(),
    commentCount = this.commentCount
)

fun CommitDetails.mapToCommitDetailsDbModel() = CommitDetailsDbModel(
    message = this.message,
    author = this.author.mapToContributorDbModel(),
    committer = this.committer.mapToContributorDbModel(),
    commentCount = this.commentCount
)

fun CommitDetailsDbModel.mapToCommitDetails() = CommitDetails(
    message = this.message,
    author = this.author.mapToCommitContributor(),
    committer = this.committer.mapToCommitContributor(),
    commentCount = this.commentCount
)