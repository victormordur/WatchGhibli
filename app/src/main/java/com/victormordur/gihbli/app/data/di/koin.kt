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
import com.victormordur.gihbli.app.data.repository.FilmRepository
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.presentation.list.FilmListViewModel
import io.ktor.client.HttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getServiceModule(httpClient: HttpClient) = module {
    single { httpClient }
    single<RemoteServiceContract.FilmService> { RemoteGihbliService(get()) }
}

fun getDbModule(application: Application, dbName: String) = module {
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, application, dbName) }
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
}

val viewModelModule = module {
    viewModel { FilmListViewModel(get(), get(), get(), get()) }
}
