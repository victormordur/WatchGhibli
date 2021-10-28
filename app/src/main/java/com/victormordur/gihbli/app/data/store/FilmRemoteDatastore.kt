package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

class FilmRemoteDatastore(
    private val service: RemoteServiceContract.FilmService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    DatastoreContract.FilmRemote {
    private val sharedFlow = MutableSharedFlow<List<Film>>()
    private val upstreamFlow = MutableSharedFlow<List<Film>>().onSubscription {
        emit(service.getAllFilms())
    }

    override fun getAllFilmsFlow(): Flow<List<Film>> =
        merge(sharedFlow, upstreamFlow).shareIn(CoroutineScope(dispatcher), SharingStarted.Eagerly)

    override suspend fun refreshFilms() {
        withContext(dispatcher) {
            sharedFlow.emit(service.getAllFilms())
        }
    }
}
