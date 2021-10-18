package com.victormordur.gihbli.app

import android.app.Application
import com.victormordur.gihbli.app.di.serviceModule
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            modules(serviceModule)
        }
    }
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }
    }
}
