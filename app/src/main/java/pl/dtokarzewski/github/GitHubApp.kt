package pl.dtokarzewski.github

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import pl.dtokarzewski.github.appinitializer.AppInitializers
import javax.inject.Inject

@HiltAndroidApp
class GitHubApp : Application() {

    @Inject lateinit var initializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        initializers.init(this)
    }
}