package com.victormordur.gihbli.app.service

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.data.service.remote.RemoteGihbliService
import com.victormordur.gihbli.app.data.service.remote.commonJsonSerializer
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class RemoteGihbliServiceTest {

    companion object {
        val jsonParser = Json { encodeDefaults = true }
    }

    @Test
    fun testGetAllFilms() {
        val films = listOf(
            Film("id1", "title1", "description1", "date1", "director1"),
            Film("id2", "title2", "description2", "date2", "director2"),
            Film("id3", "title3", "description3", "date3", "director3")
        )

        val service = RemoteGihbliService(HttpClient(MockEngine) {
            install(JsonFeature) {
                serializer = commonJsonSerializer
            }
            engine {
                addHandler { request ->
                    Assert.assertEquals(request.method, HttpMethod.Get)
                    Assert.assertEquals(
                        request.url.toString(),
                        "https://ghibliapi.herokuapp.com/films"
                    )

                    respond(
                        content = jsonParser.encodeToString(films),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType to listOf("application/json"))
                    )
                }
            }
        })

        val response = runBlocking {
            service.getAllFilms()
        }

        Assert.assertEquals(response, films)
    }
}
