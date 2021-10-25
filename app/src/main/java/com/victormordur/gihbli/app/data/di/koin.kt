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
import com.victormordur.gihbli.app.domain.repository.FilmRepository
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.simple.AddToUser
import com.victormordur.gihbli.app.domain.usecase.simple.MarkUserToBeWatched
import com.victormordur.gihbli.app.domain.usecase.simple.MarkUserWatched
import com.victormordur.gihbli.app.domain.usecase.simple.RemoveFromUser
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

val repositoryModule = module {
    single<FilmRepositoryContract> { FilmRepository(get(), get()) }
}

val useCaseModule = module {
    single { GetCatalogueFilteredByUserFilms(get()) }
    single { GetUserToBeWatchedFilms(get()) }
    single { GetUserWatchedFilms(get()) }
    single { AddToUser(get()) }
    single { RemoveFromUser(get()) }
    single { MarkUserToBeWatched(get()) }
    single { MarkUserWatched(get()) }
}
