package com.victormordur.gihbli.app.di

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.service.remote.RemoteGihbliService
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import com.victormordur.gihbli.app.data.service.remote.createHttpClient
import org.koin.dsl.module

val serviceModule = module {
    single { createHttpClient() }
    single<RemoteServiceContract.FilmService> { RemoteGihbliService(get()) }
}

fun getDbModule(application: Application) = module {
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, application) }
    single { Database(get()) }
}
