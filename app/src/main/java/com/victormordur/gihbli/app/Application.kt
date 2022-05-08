package com.victormordur.gihbli.app

import android.app.Application
import com.victormordur.gihbli.app.data.service.createHttpClient
import com.victormordur.gihbli.app.di.datastoreModule
import com.victormordur.gihbli.app.di.getDbModule
import com.victormordur.gihbli.app.di.getServiceModule
import com.victormordur.gihbli.app.di.repositoryModule
import com.victormordur.gihbli.app.di.useCaseModule
import com.victormordur.gihbli.app.di.viewModelModule
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
            modules(
                getServiceModule(httpClient),
                getDbModule(this@Application, resources.getString(R.string.db_name)),
                datastoreModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
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
