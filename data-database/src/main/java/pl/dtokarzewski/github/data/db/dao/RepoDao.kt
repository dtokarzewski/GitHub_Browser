package pl.dtokarzewski.github.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.dtokarzewski.github.data.db.model.DbRepo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg repos: DbRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repositories: List<DbRepo>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createRepoIfNotExists(repo: DbRepo): Long

    @Query("SELECT * FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    suspend fun getRepo(ownerLogin: String, name: String): DbRepo

    @Query("SELECT * FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    fun getRepoAsFlow(ownerLogin: String, name: String): Flow<DbRepo>

    @Query("DELETE FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    suspend fun deleteRepo(ownerLogin: String, name: String): Int

    @Query(
        """
        SELECT * FROM Repo
        WHERE owner_login = :owner
        ORDER BY stars DESC"""
    )
    fun getReposForOwner(owner: String): Flow<List<DbRepo>>

    @Query("SELECT * FROM repo")
    fun getAllRepos(): Flow<List<DbRepo>>
}
