package pl.dtokarzewski.github.appinitializer

import android.app.Application
import javax.inject.Inject

class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards pl.dtokarzewski.github.core.common.appinitializer.AppInitializer>
) {
    fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}