package pl.dtokarzewski.github.data.repo.mapper

import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.network.repo.model.RepoApiModel
import pl.dtokarzewski.github.data.db.model.RepoDbModel

fun Repo.mapToRepoDbModel() = RepoDbModel(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.mapToOwnerDbModel(),
        stars = this.stars
    )

fun RepoDbModel.mapToRepo() = Repo(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.mapToOwner(),
        stars = this.stars
    )

fun Repo.mapToRepoApiModel() = RepoApiModel(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.mapToOwnerApiModel(),
        stars = this.stars
    )

fun RepoApiModel.mapToRepo() = Repo(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.mapToOwner(),
        stars = this.stars
    )