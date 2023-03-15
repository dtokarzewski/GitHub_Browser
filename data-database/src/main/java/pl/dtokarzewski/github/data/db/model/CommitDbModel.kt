package pl.dtokarzewski.github.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "Commit",
    indices = [
        Index("sha"),
        Index("repoId")
    ],
    primaryKeys = ["sha"]
)
data class CommitDbModel(
    @field:Embedded
    val commit: CommitDetailsDbModel,
    val sha: String,
    val htmlUrl: String,
    val repoId: Int
) {
    companion object {
        const val UNKNOWN_ID = -1
    }
}

data class CommitDetailsDbModel(
    @field:Embedded(prefix = "author_")
    val author: ContributorDbModel,
    val commentCount: Int,
    @field:Embedded(prefix = "committer")
    val committer: ContributorDbModel,
    val message: String,
)

data class ContributorDbModel(
    val date: String,
    val email: String,
    val name: String
)
