package com.victormordur.gihbli.app.data.service.remote

import com.victormordur.gihbli.app.data.model.MovieResponse

interface RemoteServiceContract {
    interface GihbliService {
        fun getAllMovies(): List<MovieResponse>
    }
}
