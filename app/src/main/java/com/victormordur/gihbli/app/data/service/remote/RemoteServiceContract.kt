package com.victormordur.gihbli.app.data.service.remote

import com.victormordur.gihbli.app.data.model.Film

interface RemoteServiceContract {
    interface GihbliService {
        suspend fun getAllFilms(): List<Film>
    }
}
