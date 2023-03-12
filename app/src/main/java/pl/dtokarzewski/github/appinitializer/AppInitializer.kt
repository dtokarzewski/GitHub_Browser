package pl.dtokarzewski.github.appinitializer

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}