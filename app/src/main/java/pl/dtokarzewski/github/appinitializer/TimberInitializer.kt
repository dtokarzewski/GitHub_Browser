package pl.dtokarzewski.github.appinitializer

import android.app.Application
import pl.dtokarzewski.github.BuildConfig
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor() :
    pl.dtokarzewski.github.core.common.appinitializer.AppInitializer {

    override fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}