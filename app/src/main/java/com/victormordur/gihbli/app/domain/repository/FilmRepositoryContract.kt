package com.victormordur.gihbli.app.domain.repository

import com.victormordur.gihbli.app.data.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepositoryContract {
    fun getCatalogueFilms(): Flow<List<Film>>
    fun getUserFilms(): Flow<List<Film>>
    fun addToUser(film: Film)
    fun removeFromUser(id: String)
    fun markWatched(id: String)
    fun markToBeWatched(id: String)
}
