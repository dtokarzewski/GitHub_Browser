package pl.dtokarzewski.github.data.db

import androidx.annotation.OpenForTesting
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.dtokarzewski.github.data.db.model.DbRepo

/**
 * Interface for database access on Repo related operations.
 */
@Dao
@OpenForTesting
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg repos: DbRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repositories: List<DbRepo>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createRepoIfNotExists(repo: DbRepo): Long

    @Query("SELECT * FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    suspend fun getRepo(ownerLogin: String, name: String): DbRepo

    @Query("DELETE FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    suspend fun deleteRepo(ownerLogin: String, name: String)

    @Query(
        """
        SELECT * FROM Repo
        WHERE owner_login = :owner
        ORDER BY stars DESC"""
    )
    fun getReposForOwner(owner: String): Flow<List<DbRepo>>
}
