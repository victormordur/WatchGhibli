package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.data.model.Film

interface DatastoreContract {
    interface FilmRemote {
        suspend fun getAllFilms(): List<Film>
    }
    interface FilmLocal {
        suspend fun getAll(): List<Film>
        fun add(film: Film)
        fun remove(id: String)
        fun updateWatched(watched: Boolean, id: String)
    }
}
