package com.victormordur.gihbli.app.data.service.remote

import com.victormordur.gihbli.app.data.model.MovieResponse
import io.ktor.client.HttpClient

class RemoteGihbliService(private val httpClient: HttpClient) :
    RemoteServiceContract.GihbliService {
    override fun getAllMovies(): List<MovieResponse> {
        TODO("Not yet implemented")
    }
}
