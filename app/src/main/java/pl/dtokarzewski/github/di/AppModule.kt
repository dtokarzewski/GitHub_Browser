package pl.dtokarzewski.github.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import pl.dtokarzewski.github.appinitializer.TimberInitializer

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

    @Binds
    @IntoSet
    abstract fun bindTimberInitializer(bind: TimberInitializer): pl.dtokarzewski.github.core.common.appinitializer.AppInitializer
}