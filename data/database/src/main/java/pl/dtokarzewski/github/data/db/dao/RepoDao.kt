package pl.dtokarzewski.github.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.dtokarzewski.github.data.db.model.RepoDbModel

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg repos: RepoDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repositories: List<RepoDbModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createRepoIfNotExists(repo: RepoDbModel): Long

    @Query("SELECT * FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    suspend fun getRepo(ownerLogin: String, name: String): RepoDbModel

    @Query("SELECT * FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    fun getRepoAsFlow(ownerLogin: String, name: String): Flow<RepoDbModel>

    @Query("DELETE FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    suspend fun deleteRepo(ownerLogin: String, name: String): Int

    @Query(
        """
        SELECT * FROM Repo
        WHERE owner_login = :owner
        ORDER BY stars DESC"""
    )
    fun getReposForOwner(owner: String): Flow<List<RepoDbModel>>

    @Query("SELECT * FROM repo")
    fun getAllRepos(): Flow<List<RepoDbModel>>
}
