package pl.dtokarzewski.github.data.commit.mapper

import pl.dtokarzewski.github.core.model.Contributor
import pl.dtokarzewski.github.core.network.commit.model.ContributorApiModel
import pl.dtokarzewski.github.data.db.model.ContributorDbModel


fun ContributorApiModel.mapToContributor() = Contributor(
    date = this.date,
    name = this.name,
    email = this.email
)

fun Contributor.mapToContributorDbModel() = ContributorDbModel(
    date = this.date,
    name = this.name,
    email = this.email
)

fun ContributorDbModel.mapToCommitContributor() = Contributor(
    date = this.date,
    name = this.name,
    email = this.email
)