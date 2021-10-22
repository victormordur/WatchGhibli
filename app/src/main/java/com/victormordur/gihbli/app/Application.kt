package com.victormordur.gihbli.app

import android.app.Application
import com.victormordur.gihbli.app.data.di.datastoreModule
import com.victormordur.gihbli.app.data.di.getDbModule
import com.victormordur.gihbli.app.data.di.getServiceModule
import com.victormordur.gihbli.app.data.service.remote.createHttpClient
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : Application() {
    private val httpClient by lazy { createHttpClient() }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            modules(getServiceModule(httpClient), getDbModule(this@Application), datastoreModule)
        }
    }
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }
    }
}
