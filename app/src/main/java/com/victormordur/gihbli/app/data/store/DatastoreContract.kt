package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.data.model.Film
import kotlinx.coroutines.flow.Flow

interface DatastoreContract {
    interface FilmRemote {
        fun getAllFilms(): Flow<List<Film>>
    }
    interface FilmLocal {
        fun getAll(): Flow<List<Film>>
        fun add(film: Film)
        fun remove(id: String)
        fun updateWatched(watched: Boolean, id: String)
    }
}
