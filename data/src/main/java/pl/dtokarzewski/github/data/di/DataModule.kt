package pl.dtokarzewski.github.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.dtokarzewski.github.data.commit.CommitRepository
import pl.dtokarzewski.github.data.commit.CommitRepositoryImpl
import pl.dtokarzewski.github.data.repo.RepoRepository
import pl.dtokarzewski.github.data.repo.RepoRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindRepoRepository(repository: RepoRepositoryImpl): RepoRepository

    @Binds
    fun bindCommitRepository(repository: CommitRepositoryImpl): CommitRepository
}