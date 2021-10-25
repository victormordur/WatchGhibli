package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRemoteDatastore(private val service: RemoteServiceContract.FilmService) :
    DatastoreContract.FilmRemote {
    override fun getAllFilms(): Flow<List<Film>> = flow {
        emit(service.getAllFilms())
    }
}
