package com.victormordur.gihbli.app.data.service.remote

import com.victormordur.gihbli.app.domain.model.Film

interface FilmService {
    suspend fun getAllFilms(): List<Film>
}
