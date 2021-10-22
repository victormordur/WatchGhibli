package com.victormordur.gihbli.app.data.di

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.service.remote.RemoteGihbliService
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import com.victormordur.gihbli.app.data.store.DatastoreContract
import com.victormordur.gihbli.app.data.store.FilmLocalDatastore
import com.victormordur.gihbli.app.data.store.FilmRemoteDatastore
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun getServiceModule(httpClient: HttpClient) = module {
    single { httpClient }
    single<RemoteServiceContract.FilmService> { RemoteGihbliService(get()) }
}

fun getDbModule(application: Application) = module {
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, application) }
    single { Database(get()) }
}

val datastoreModule = module {
    single<DatastoreContract.FilmRemote> { FilmRemoteDatastore(get()) }
    single<DatastoreContract.FilmLocal> { FilmLocalDatastore(get()) }
}
