package pl.dtokarzewski.github.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.dtokarzewski.github.data.repository.RepoRepository
import pl.dtokarzewski.github.data.repository.RepoRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindRepoRepository(repository: RepoRepositoryImpl): RepoRepository
}