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
data class DbRepo(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    @field:Embedded(prefix = "owner_")
    val owner: Owner,
    val stars: Int
) {

    data class Owner(
        val login: String,
        val url: String?
    )

    companion object {
        const val UNKNOWN_ID = -1
    }
}