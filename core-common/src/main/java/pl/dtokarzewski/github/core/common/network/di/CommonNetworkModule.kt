package pl.dtokarzewski.github.core.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import pl.dtokarzewski.github.core.common.network.DefaultDispatcher
import pl.dtokarzewski.github.core.common.network.IoDispatcher
import pl.dtokarzewski.github.core.common.network.MainDispatcher
import pl.dtokarzewski.github.core.common.network.MainImmediateDispatcher

@InstallIn(SingletonComponent::class)
@Module
object CommonNetworkModule {

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}