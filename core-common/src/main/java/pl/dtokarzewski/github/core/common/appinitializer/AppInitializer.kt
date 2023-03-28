package pl.dtokarzewski.github.core.common.appinitializer

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}