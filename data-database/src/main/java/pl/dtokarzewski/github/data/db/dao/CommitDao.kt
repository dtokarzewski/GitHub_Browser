package pl.dtokarzewski.github.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.dtokarzewski.github.data.db.model.CommitDbModel

@Dao
interface CommitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg commits: CommitDbModel)

    @Query("SELECT * FROM `commit`")
    abstract fun getPagedCommits(): PagingSource<Int, CommitDbModel>
}