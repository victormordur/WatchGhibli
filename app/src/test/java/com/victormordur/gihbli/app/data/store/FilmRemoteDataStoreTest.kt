package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test

class FilmRemoteDataStoreTest {
    private val service: RemoteServiceContract.FilmService = mockk()
    private val datastore = FilmRemoteDatastore(service)

    private val films = listOf(1, 2, 3, 4, 5).map {
        Film(
            "id$it",
            "title$it",
            "description$it",
            "date$it",
            "director$it",
            "imageURL$it",
            false
        )
    }

    @After
    fun tearDown() {
        confirmVerified(service)
    }

    @Test
    fun testGetAllFilms() {
        coEvery { service.getAllFilms() } returns films
        val result = runBlocking {
            datastore.getAllFilms()
        }
        Assert.assertEquals(result, films)
        coVerify { service.getAllFilms() }
    }
}
