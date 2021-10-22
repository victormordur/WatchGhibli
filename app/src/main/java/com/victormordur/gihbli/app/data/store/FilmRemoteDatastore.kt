package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract

class FilmRemoteDatastore(private val service: RemoteServiceContract.FilmService) :
    DatastoreContract.FilmRemote {
    override suspend fun getAllFilms(): List<Film> {
        return service.getAllFilms()
    }
}
