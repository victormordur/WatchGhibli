package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.data.model.Film
import kotlinx.coroutines.flow.Flow

interface DatastoreContract {
    interface FilmRemote {
        fun getAllFilmsFlow(): Flow<List<Film>>
        suspend fun refreshFilms()
    }
    interface FilmLocal {
        fun getAllFlow(): Flow<List<Film>>
        fun add(film: Film)
        fun remove(id: String)
        fun updateWatched(watched: Boolean, id: String)
    }
}
