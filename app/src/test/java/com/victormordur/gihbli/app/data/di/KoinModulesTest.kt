package com.victormordur.gihbli.app.data.di

import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.service.remote.RemoteGihbliService
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import com.victormordur.gihbli.app.data.service.remote.createHttpClient
import com.victormordur.gihbli.app.data.store.DatastoreContract
import com.victormordur.gihbli.app.data.store.FilmLocalDatastore
import com.victormordur.gihbli.app.data.store.FilmRemoteDatastore
import com.victormordur.gihbli.app.domain.repository.FilmRepository
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
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
        getDbModule(ApplicationProvider.getApplicationContext()),
        datastoreModule,
        repositoryModule
    )

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun testServiceModuleInstances() {
        val app = startKoin { modules(koinModules) }
        val httpClient: HttpClient = app.koin.get()
        val gihbliService: RemoteServiceContract.FilmService = app.koin.get()
        Assert.assertNotNull(httpClient)
        Assert.assertNotNull(gihbliService)
        Assert.assertTrue(gihbliService is RemoteGihbliService)
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
        val remote: DatastoreContract.FilmRemote = app.koin.get()
        val local: DatastoreContract.FilmLocal = app.koin.get()
        Assert.assertNotNull(remote)
        Assert.assertTrue(remote is FilmRemoteDatastore)
        Assert.assertNotNull(local)
        Assert.assertTrue(local is FilmLocalDatastore)
    }

    @Test
    fun testRepositoryModuleInstances() {
        val app = startKoin { modules(koinModules) }
        val repository: FilmRepositoryContract = app.koin.get()
        Assert.assertNotNull(repository)
        Assert.assertTrue(repository is FilmRepository)
    }
}
