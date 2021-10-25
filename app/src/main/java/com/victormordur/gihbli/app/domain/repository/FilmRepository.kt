package com.victormordur.gihbli.app.domain.repository

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.data.store.DatastoreContract
import kotlinx.coroutines.flow.Flow

class FilmRepository(
    private val remote: DatastoreContract.FilmRemote,
    private val local: DatastoreContract.FilmLocal
) : FilmRepositoryContract {
    override fun getCatalogueFilms(): Flow<List<Film>> {
        return remote.getAllFilms()
    }

    override fun getUserFilms(): Flow<List<Film>> {
        return local.getAll()
    }

    override fun addToUser(film: Film) {
        local.add(film)
    }

    override fun removeFromUser(id: String) {
        local.remove(id)
    }

    override fun markWatched(id: String) {
        local.updateWatched(true, id)
    }

    override fun markToBeWatched(id: String) {
        local.updateWatched(false, id)
    }
}
