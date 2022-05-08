package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmDatastore {
    interface Remote {
        fun getAllFilmsFlow(): Flow<List<Film>>
        suspend fun refreshFilms()
    }
    interface Local {
        fun getAllFlow(): Flow<List<Film>>
        fun add(film: Film)
        fun remove(id: String)
        fun updateWatched(watched: Boolean, id: String)
    }
}
