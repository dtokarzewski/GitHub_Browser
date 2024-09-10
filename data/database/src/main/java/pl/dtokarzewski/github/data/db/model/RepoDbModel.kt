package pl.dtokarzewski.github.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "Repo",
    indices = [
        Index("id"),
        Index("owner_login")],
    primaryKeys = ["name", "owner_login"]
)
data class RepoDbModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    @field:Embedded(prefix = "owner_")
    val owner: OwnerDbModel,
    val stars: Int
) {
    companion object {
        const val UNKNOWN_ID = -1
    }
}

data class OwnerDbModel(
    val login: String,
    val url: String?
)
