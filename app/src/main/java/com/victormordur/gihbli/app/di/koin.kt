package com.victormordur.gihbli.app.di

import com.victormordur.gihbli.app.data.service.remote.RemoteGihbliService
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import com.victormordur.gihbli.app.data.service.remote.createHttpClient
import org.koin.dsl.module

val serviceModule = module {
    single { createHttpClient() }
    single<RemoteServiceContract.GihbliService> { RemoteGihbliService(get()) }
}
