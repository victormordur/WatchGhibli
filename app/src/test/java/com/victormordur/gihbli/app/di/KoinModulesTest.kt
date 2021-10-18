package com.victormordur.gihbli.app.di

import com.victormordur.gihbli.app.data.service.remote.RemoteGihbliService
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import io.ktor.client.HttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication

class KoinModulesTest {
    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun testServiceModuleInstances() {
        val app = koinApplication { modules(serviceModule) }
        val httpClient: HttpClient = app.koin.get()
        val gihbliService: RemoteServiceContract.GihbliService = app.koin.get()
        Assert.assertNotNull(httpClient)
        Assert.assertNotNull(gihbliService)
        Assert.assertTrue(gihbliService is RemoteGihbliService)
    }

}
