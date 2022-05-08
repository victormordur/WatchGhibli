package com.victormordur.gihbli.app.data.repository

import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.data.store.DatastoreContract
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import kotlinx.coroutines.flow.Flow

class FilmRepository(
    private val remote: DatastoreContract.FilmRemote,
    private val local: DatastoreContract.FilmLocal
) : FilmRepositoryContract {
    override fun getCatalogueFilmsFlow(): Flow<List<Film>> {
        return remote.getAllFilmsFlow()
    }

    override suspend fun refreshCatalogueFilms() {
        remote.refreshFilms()
    }

    override fun getUserFilmsFlow(): Flow<List<Film>> {
        return local.getAllFlow()
    }

    override suspend fun addToUser(film: Film) {
        local.add(film)
    }

    override suspend fun removeFromUser(id: String) {
        local.remove(id)
    }

    override suspend fun markWatched(id: String) {
        local.updateWatched(true, id)
    }

    override suspend fun markToBeWatched(id: String) {
        local.updateWatched(false, id)
    }
}
