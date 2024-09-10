package pl.dtokarzewski.github.data.db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.dtokarzewski.github.data.db.GithubDatabase
import pl.dtokarzewski.github.data.db.dao.CommitDao
import pl.dtokarzewski.github.data.db.dao.RepoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGithubDatabase(
        @ApplicationContext context: Context
    ): GithubDatabase = Room.databaseBuilder(
        context,
        GithubDatabase::class.java,
        "github-database"
    ).build()

    @Provides
    fun provideRepoDao(
        database: GithubDatabase
    ): RepoDao = database.repoDao()

    @Provides
    fun provideCommitDao(
        database: GithubDatabase
    ) : CommitDao = database.commitDao()
}