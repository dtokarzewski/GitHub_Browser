package pl.dtokarzewski.github.data.repo.mapper

import pl.dtokarzewski.github.core.model.Owner
import pl.dtokarzewski.github.core.network.repo.model.OwnerApiModel
import pl.dtokarzewski.github.data.db.model.OwnerDbModel

fun Owner.mapToOwnerDbModel() = OwnerDbModel(
    login = this.login,
    url = this.url
)

fun OwnerDbModel.mapToOwner() = Owner(
    login = this.login,
    url = this.url
)

fun Owner.mapToOwnerApiModel() = OwnerApiModel(
    login = this.login,
    url = this.url
)

fun OwnerApiModel.mapToOwner() = Owner(
    login = this.login,
    url = this.url
)