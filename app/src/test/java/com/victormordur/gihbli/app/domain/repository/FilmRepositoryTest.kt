package com.victormordur.gihbli.app.domain.repository

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.data.store.DatastoreContract
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert
import org.junit.Test

class FilmRepositoryTest {
    private val remoteDatastore: DatastoreContract.FilmRemote = mockk()
    private val localDatastore: DatastoreContract.FilmLocal = mockk()
    private val repository = FilmRepository(remoteDatastore, localDatastore)

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
        confirmVerified(remoteDatastore, localDatastore)
    }

    @Test
    fun testGetCatalogueFilms() {
        val testFlow = flowOf(films)
        coEvery { remoteDatastore.getAllFilms() } returns testFlow
        val response = repository.getCatalogueFilms()
        Assert.assertEquals(response, testFlow)
        coVerify { remoteDatastore.getAllFilms() }
    }

    @Test
    fun testGetUserFilms() {
        val testFlow = flowOf(films)
        coEvery { localDatastore.getAll() } returns testFlow
        val response = repository.getUserFilms()
        Assert.assertEquals(response, testFlow)
        coVerify { localDatastore.getAll() }
    }

    @Test
    fun testAddToUser() {
        val film = Film("id", "title", "description", "date", "director", "imageURL", false)
        coEvery { localDatastore.add(film) } just Runs
        repository.addToUser(film)
        coVerify { localDatastore.add(film) }
    }

    @Test
    fun testRemoveFromUser() {
        val id = "id"
        coEvery { localDatastore.remove(id) } just Runs
        repository.removeFromUser(id)
        coVerify { localDatastore.remove(id) }
    }

    @Test
    fun testMarkWatched() {
        val id = "id"
        coEvery { localDatastore.updateWatched(true, id) } just Runs
        repository.markWatched(id)
        coVerify { localDatastore.updateWatched(true, id) }
    }

    @Test
    fun testMarkToBeWatched() {
        val id = "id"
        coEvery { localDatastore.updateWatched(false, id) } just Runs
        repository.markToBeWatched(id)
        coVerify { localDatastore.updateWatched(false, id) }
    }
}
