package pl.dtokarzewski.github.data.test.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import pl.dtokarzewski.github.data.di.DataModule
import pl.dtokarzewski.github.data.repo.FakeRepoRepository
import pl.dtokarzewski.github.data.repo.RepoRepository

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
interface TestDataModule {

    @Binds
    fun bindRepoRepository(fakeRepository: FakeRepoRepository): RepoRepository
}