package com.victormordur.gihbli.app.domain.repository

import com.victormordur.gihbli.app.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepositoryContract {
    fun getCatalogueFilmsFlow(): Flow<List<Film>>
    suspend fun refreshCatalogueFilms()
    fun getUserFilmsFlow(): Flow<List<Film>>
    suspend fun addToUser(film: Film)
    suspend fun removeFromUser(id: String)
    suspend fun markWatched(id: String)
    suspend fun markToBeWatched(id: String)
}
