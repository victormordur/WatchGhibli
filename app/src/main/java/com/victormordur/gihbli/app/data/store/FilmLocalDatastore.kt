package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.model.Film

typealias DBFilm = gihbli.Film

class FilmLocalDatastore(private val database: Database) : DatastoreContract.FilmLocal {
    override suspend fun getAll(): List<Film> {
        return database.filmQueries.selectAll().executeAsList().map { it.toFilm() }
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
