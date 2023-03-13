package pl.dtokarzewski.github.data.mapper

import pl.dtokarzewski.github.core.common.mapper.Mapper
import pl.dtokarzewski.github.core.model.Repo
import pl.dtokarzewski.github.core.network.model.NetworkRepo
import pl.dtokarzewski.github.data.db.model.DbRepo

object RepoToDbRepoMapper : Mapper<Repo, DbRepo> {
    override fun map(input: Repo) = DbRepo(
        id = input.id,
        name = input.name,
        fullName = input.fullName,
        description = input.description,
        owner = OwnerToDbOwnerMapper.map(input.owner),
        stars = input.stars
    )
}

object DbRepoToRepoMapper : Mapper<DbRepo, Repo> {
    override fun map(input: DbRepo) = Repo(
        id = input.id,
        name = input.name,
        fullName = input.fullName,
        description = input.description,
        owner = DbOwnerToOwnerMapper.map(input.owner),
        stars = input.stars
    )
}

object RepoToNetworkRepoMapper : Mapper<Repo, NetworkRepo> {
    override fun map(input: Repo) = NetworkRepo(
        id = input.id,
        name = input.name,
        fullName = input.fullName,
        description = input.description,
        owner = OwnerToNetworkOwnerMapper.map(input.owner),
        stars = input.stars
    )
}

object NetworkRepoToRepoMapper : Mapper<NetworkRepo, Repo> {
    override fun map(input: NetworkRepo) = Repo(
        id = input.id,
        name = input.name,
        fullName = input.fullName,
        description = input.description,
        owner = NetworkOwnerToOwnerMapper.map(input.owner),
        stars = input.stars
    )
}

object OwnerToDbOwnerMapper : Mapper<Repo.Owner, DbRepo.Owner> {
    override fun map(input: Repo.Owner) = DbRepo.Owner(
        login = input.login,
        url = input.url
    )
}

object DbOwnerToOwnerMapper : Mapper<DbRepo.Owner, Repo.Owner> {
    override fun map(input: DbRepo.Owner)= Repo.Owner(
        login = input.login,
        url = input.url
    )
}

object OwnerToNetworkOwnerMapper : Mapper<Repo.Owner, NetworkRepo.Owner> {
    override fun map(input: Repo.Owner) = NetworkRepo.Owner(
        login = input.login,
        url = input.url
    )
}

object NetworkOwnerToOwnerMapper : Mapper<NetworkRepo.Owner, Repo.Owner> {
    override fun map(input: NetworkRepo.Owner) = Repo.Owner(
        login = input.login,
        url = input.url
    )
}