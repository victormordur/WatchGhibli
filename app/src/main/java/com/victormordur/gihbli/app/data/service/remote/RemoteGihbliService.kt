package com.victormordur.gihbli.app.data.service.remote

import com.victormordur.gihbli.app.domain.model.Film
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteGihbliService(private val client: HttpClient) :
    RemoteServiceContract.FilmService {

    companion object {
        const val baseUrl = "https://ghibliapi.herokuapp.com"
        object Endpoints {
            const val films = "films"
        }
    }

    override suspend fun getAllFilms(): List<Film> {
        return client.get("$baseUrl/${Endpoints.films}")
    }
}
