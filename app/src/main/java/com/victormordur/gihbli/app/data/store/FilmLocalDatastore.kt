package com.victormordur.gihbli.app.data.store

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.model.Film
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

typealias DBFilm = gihbli.Film

class FilmLocalDatastore(
    private val database: Database,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : DatastoreContract.FilmLocal {
    override fun getAll(): Flow<List<Film>> {
        return database.filmQueries.selectAll().asFlow().mapToList(dispatcher).mapLatest { list ->
            list.map { it.toFilm() }
        }
    }

    override fun add(film: Film) {
        database.filmQueries.insert(
            film.id,
            film.title,
            film.description,
            film.releaseDate,
            film.director,
            film.imageURL,
            film.watched
        )
    }

    override fun remove(id: String) {
        database.filmQueries.delete(id)
    }

    override fun updateWatched(watched: Boolean, id: String) {
        database.filmQueries.updateWatched(watched, id)
    }

    private fun DBFilm.toFilm() =
        Film(id, title, description, releaseDate, director, imageURL, watched)
}
