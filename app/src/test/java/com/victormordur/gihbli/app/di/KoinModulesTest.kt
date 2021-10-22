package com.victormordur.gihbli.app.di

import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.service.remote.RemoteGihbliService
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import com.victormordur.gihbli.app.data.store.DatastoreContract
import com.victormordur.gihbli.app.data.store.FilmLocalDatastore
import com.victormordur.gihbli.app.data.store.FilmRemoteDatastore
import io.ktor.client.HttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class KoinModulesTest {
    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun testServiceModuleInstances() {
        val app = koinApplication { modules(serviceModule) }
        val httpClient: HttpClient = app.koin.get()
        val gihbliService: RemoteServiceContract.FilmService = app.koin.get()
        Assert.assertNotNull(httpClient)
        Assert.assertNotNull(gihbliService)
        Assert.assertTrue(gihbliService is RemoteGihbliService)
    }

    @Test
    fun testDbModuleInstances() {
        val app =
            koinApplication { modules(getDbModule(ApplicationProvider.getApplicationContext())) }
        val sqlDriver: SqlDriver = app.koin.get()
        val database: Database = app.koin.get()
        Assert.assertNotNull(sqlDriver)
        Assert.assertTrue(sqlDriver is AndroidSqliteDriver)
        Assert.assertNotNull(database)
    }

    @Test
    fun testDatastoreModuleInstances() {
        val app = koinApplication {
            modules(
                serviceModule,
                getDbModule(ApplicationProvider.getApplicationContext()),
                datastoreModule
            )
        }
        val remote: DatastoreContract.FilmRemote = app.koin.get()
        val local: DatastoreContract.FilmLocal = app.koin.get()
        Assert.assertNotNull(remote)
        Assert.assertTrue(remote is FilmRemoteDatastore)
        Assert.assertNotNull(local)
        Assert.assertTrue(local is FilmLocalDatastore)
    }
}
