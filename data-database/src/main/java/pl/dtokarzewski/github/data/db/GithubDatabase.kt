package pl.dtokarzewski.github.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.dtokarzewski.github.data.db.dao.RepoDao
import pl.dtokarzewski.github.data.db.model.DbCommit
import pl.dtokarzewski.github.data.db.model.DbRepo

@Database(
    entities = [
        DbCommit::class,
        DbRepo::class],
    version = 4,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}
