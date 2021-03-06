package com.victormordur.gihbli.app.data.store

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.domain.model.Film
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

typealias DBFilm = gihbli.Film

class FilmLocalDatastoreImpl(
    private val database: Database,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : FilmDatastore.Local {
    override fun getAllFlow(): Flow<List<Film>> {
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
