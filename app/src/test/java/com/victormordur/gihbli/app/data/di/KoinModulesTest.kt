package com.victormordur.gihbli.app.data.di

import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.service.remote.FilmServiceGihbliImpl
import com.victormordur.gihbli.app.data.service.remote.FilmService
import com.victormordur.gihbli.app.data.service.remote.createHttpClient
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
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class KoinModulesTest {

    private val httpClient = createHttpClient()
    private val koinModules: List<Module> = listOf(
        getServiceModule(httpClient),
        getDbModule(ApplicationProvider.getApplicationContext(), "TestDbName.db"),
        datastoreModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun testServiceModuleInstances() {
        val app = startKoin { modules(koinModules) }
        val httpClient: HttpClient = app.koin.get()
        val gihbliService: FilmService = app.koin.get()
        Assert.assertNotNull(httpClient)
        Assert.assertNotNull(gihbliService)
        Assert.assertTrue(gihbliService is FilmServiceGihbliImpl)
    }

    @Test
    fun testDbModuleInstances() {
        val app =
            startKoin { modules(koinModules) }
        val sqlDriver: SqlDriver = app.koin.get()
        val database: Database = app.koin.get()
        Assert.assertNotNull(sqlDriver)
        Assert.assertTrue(sqlDriver is AndroidSqliteDriver)
        Assert.assertNotNull(database)
    }

    @Test
    fun testDatastoreModuleInstances() {
        val app = startKoin { modules(koinModules) }
        val remote: FilmDatastore.Remote = app.koin.get()
        val local: FilmDatastore.Local = app.koin.get()
        Assert.assertNotNull(remote)
        Assert.assertTrue(remote is FilmRemoteDatastoreImpl)
        Assert.assertNotNull(local)
        Assert.assertTrue(local is FilmLocalDatastoreImpl)
    }

    @Test
    fun testRepositoryModuleInstances() {
        val app = startKoin { modules(koinModules) }
        val repository: FilmRepository = app.koin.get()
        Assert.assertNotNull(repository)
        Assert.assertTrue(repository is FilmRepositoryImpl)
    }

    @Test
    fun testUseCaseModuleInstances() {
        val app = startKoin { modules(koinModules) }
        val getCatalogueFiltered: GetCatalogueFilteredByUserFilms = app.koin.get()
        val getUserToBeWatched: GetUserToBeWatchedFilms = app.koin.get()
        val getUserWatched: GetUserWatchedFilms = app.koin.get()
        Assert.assertNotNull(getCatalogueFiltered)
        Assert.assertNotNull(getUserToBeWatched)
        Assert.assertNotNull(getUserWatched)
    }

    @Test
    fun testViewModelModuleInstance() {
        val app = startKoin { modules(koinModules) }
        val filmListViewModel: FilmListViewModel = app.koin.get()
        Assert.assertNotNull(filmListViewModel)
    }
}
