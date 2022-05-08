package com.victormordur.gihbli.app.data.store

import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.data.service.remote.RemoteServiceContract
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Test

class FilmRemoteDataStoreTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val service: RemoteServiceContract.FilmService = mockk()
    private val datastore = FilmRemoteDatastore(service, testCoroutineDispatcher)

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
    fun testGetAllFilms() = testCoroutineDispatcher.runBlockingTest {
        coEvery { service.getAllFilms() } returns films
        datastore.getAllFilmsFlow().onEach {
            Assert.assertEquals(it, films)
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        testCoroutineDispatcher.advanceUntilIdle()
        coVerify { service.getAllFilms() }
    }

    @Test
    fun testRefreshFilms() = testCoroutineDispatcher.runBlockingTest {
        coEvery { service.getAllFilms() } returns films andThen emptyList()
        var list = films
        datastore.getAllFilmsFlow().onEach {
            Assert.assertEquals(it, list)
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        testCoroutineDispatcher.advanceUntilIdle()
        list = emptyList()
        datastore.refreshFilms()
        testCoroutineDispatcher.advanceUntilIdle()
        coVerify(exactly = 2) { service.getAllFilms() }
    }
}
