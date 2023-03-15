package pl.dtokarzewski.github.data.mapper

import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.network.model.NetworkRepo
import pl.dtokarzewski.github.data.db.model.DbRepo

fun Repo.mapToDbRepo() = DbRepo(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.mapToDbRepoOwner(),
        stars = this.stars
    )

fun DbRepo.mapToRepo() = Repo(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.mapToRepoOwner(),
        stars = this.stars
    )

fun Repo.mapToNetworkRepo() = NetworkRepo(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.mapToNetworkOwner(),
        stars = this.stars
    )

fun NetworkRepo.mapToRepo() = Repo(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description,
        owner = this.owner.mapToRepoOwner(),
        stars = this.stars
    )

fun Repo.Owner.mapToDbRepoOwner() = DbRepo.Owner(
        login = this.login,
        url = this.url
    )

fun DbRepo.Owner.mapToRepoOwner() = Repo.Owner(
        login = this.login,
        url = this.url
    )

fun Repo.Owner.mapToNetworkOwner() = NetworkRepo.Owner(
        login = this.login,
        url = this.url
    )

fun NetworkRepo.Owner.mapToRepoOwner() = Repo.Owner(
        login = this.login,
        url = this.url
    )