package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.data.service.FilmService
import com.victormordur.gihbli.app.domain.model.Film
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class FilmRemoteDatastoreImpl(
    private val service: FilmService,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    FilmDatastore.Remote, RefreshableFlowDatastore<List<Film>>(dispatcher) {

    override suspend fun onSubscription() = service.getAllFilms()
    override suspend fun onRefresh() = service.getAllFilms()

    override fun getAllFilmsFlow(): Flow<List<Film>> = getRefreshableFlow()

    override suspend fun refreshFilms() {
        refresh()
    }
}
