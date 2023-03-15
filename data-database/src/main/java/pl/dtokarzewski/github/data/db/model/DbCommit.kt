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
class DbCommit(
    @field:Embedded
    val commit: CommitDetails,
    val sha: String,
    val htmlUrl: String,
    val repoId: Int
) {
    data class CommitDetails(
        @field:Embedded(prefix = "author_")
        val author: Contributor,
        val commentCount: Int,
        @field:Embedded(prefix = "committer")
        val committer: Contributor,
        val message: String,
    )

    data class Contributor(
        val date: String,
        val email: String,
        val name: String
    )

    companion object {
        const val UNKNOWN_ID = -1
    }
}