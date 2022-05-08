package com.victormordur.gihbli.app.data.service

import com.victormordur.gihbli.app.domain.model.Film

interface FilmService {
    suspend fun getAllFilms(): List<Film>
}
