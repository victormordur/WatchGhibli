package com.victormordur.gihbli.app.data.repository

import com.victormordur.gihbli.app.data.store.FilmDatastore
import com.victormordur.gihbli.app.domain.model.Film
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test

class FilmRepositoryImplTest {
    private val remoteDatastore: FilmDatastore.Remote = mockk()
    private val localDatastore: FilmDatastore.Local = mockk()
    private val repository = FilmRepositoryImpl(remoteDatastore, localDatastore)

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
        coEvery { remoteDatastore.getAllFilmsFlow() } returns testFlow
        val response = repository.getCatalogueFilmsFlow()
        Assert.assertEquals(response, testFlow)
        coVerify { remoteDatastore.getAllFilmsFlow() }
    }

    @Test
    fun testRefreshCatalogueFilms() {
        coEvery { remoteDatastore.refreshFilms() } just Runs
        runBlocking {
            repository.refreshCatalogueFilms()
        }
        coVerify { remoteDatastore.refreshFilms() }
    }

    @Test
    fun testGetUserFilms() {
        val testFlow = flowOf(films)
        coEvery { localDatastore.getAllFlow() } returns testFlow
        val response = repository.getUserFilmsFlow()
        Assert.assertEquals(response, testFlow)
        coVerify { localDatastore.getAllFlow() }
    }

    @Test
    fun testAddToUser() {
        val film = Film("id", "title", "description", "date", "director", "imageURL", false)
        coEvery { localDatastore.add(film) } just Runs
        runBlocking {
            repository.addToUser(film)
        }
        coVerify { localDatastore.add(film) }
    }

    @Test
    fun testRemoveFromUser() {
        val id = "id"
        coEvery { localDatastore.remove(id) } just Runs
        runBlocking {
            repository.removeFromUser(id)
        }
        coVerify { localDatastore.remove(id) }
    }

    @Test
    fun testMarkWatched() {
        val id = "id"
        coEvery { localDatastore.updateWatched(true, id) } just Runs
        runBlocking {
            repository.markWatched(id)
        }
        coVerify { localDatastore.updateWatched(true, id) }
    }

    @Test
    fun testMarkToBeWatched() {
        val id = "id"
        coEvery { localDatastore.updateWatched(false, id) } just Runs
        runBlocking {
            repository.markToBeWatched(id)
        }
        coVerify { localDatastore.updateWatched(false, id) }
    }
}
