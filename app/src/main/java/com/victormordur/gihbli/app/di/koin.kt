package com.victormordur.gihbli.app.di

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.service.remote.FilmServiceGihbliImpl
import com.victormordur.gihbli.app.data.service.remote.FilmService
import com.victormordur.gihbli.app.data.store.FilmDatastore
import com.victormordur.gihbli.app.data.store.FilmLocalDatastoreImpl
import com.victormordur.gihbli.app.data.store.FilmRemoteDatastoreImpl
import com.victormordur.gihbli.app.data.repository.FilmRepositoryImpl
import com.victormordur.gihbli.app.domain.repository.FilmRepository
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.presentation.list.FilmListViewModel
import io.ktor.client.HttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getServiceModule(httpClient: HttpClient) = module {
    single { httpClient }
    single<FilmService> { FilmServiceGihbliImpl(get()) }
}

fun getDbModule(application: Application, dbName: String) = module {
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, application, dbName) }
    single { Database(get()) }
}

val datastoreModule = module {
    single<FilmDatastore.Remote> { FilmRemoteDatastoreImpl(get()) }
    single<FilmDatastore.Local> { FilmLocalDatastoreImpl(get()) }
}

val repositoryModule = module {
    single<FilmRepository> { FilmRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    single { GetCatalogueFilteredByUserFilms(get()) }
    single { GetUserToBeWatchedFilms(get()) }
    single { GetUserWatchedFilms(get()) }
}

val viewModelModule = module {
    viewModel { FilmListViewModel(get(), get(), get(), get()) }
}
