package pl.dtokarzewski.github.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.dtokarzewski.github.data.db.model.DbRepo

@Database(
    entities = [
        DbRepo::class],
    version = 3,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}
