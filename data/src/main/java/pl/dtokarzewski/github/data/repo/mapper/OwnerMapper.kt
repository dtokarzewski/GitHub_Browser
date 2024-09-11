package pl.dtokarzewski.github.data.repo.mapper

import pl.dtokarzewski.github.core.model.Owner
import pl.dtokarzewski.github.core.network.repo.model.OwnerApiModel
import pl.dtokarzewski.github.data.db.model.OwnerDbModel

fun Owner.toDbModel() = OwnerDbModel(
    login = this.login,
    url = this.url
)

fun OwnerDbModel.toDomainModel() = Owner(
    login = this.login,
    url = this.url
)

fun Owner.toApiModel() = OwnerApiModel(
    login = this.login,
    url = this.url
)

fun OwnerApiModel.toDomainModel() = Owner(
    login = this.login,
    url = this.url
)