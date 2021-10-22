package com.victormordur.gihbli.app.data.store

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.victormordur.gihbli.app.Database
import com.victormordur.gihbli.app.data.model.Film
import gihbli.FilmQueries
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class FilmLocalDataStoreTest {
    private val database: Database = mockk()
    private val queries: FilmQueries = mockk()
    private val datastore = FilmLocalDatastore(database)

    private val itemList = listOf(1, 2, 3, 4, 5)
    private val dbFilms = itemList.map {
        DBFilm(
            "id$it",
            "title$it",
            "description$it",
            "date$it",
            "director$it",
            "imageURL$it",
            false
        )
    }

    private val films = itemList.map {
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

    @Before
    fun setUp() {
        coEvery { database.filmQueries } returns queries
    }

    @After
    fun tearDown() {
        confirmVerified(database, queries)
    }

    @Test
    @Ignore
    fun testSelectAllFilms() {
        coEvery { queries.selectAll().asFlow().mapToList() } returns flowOf(dbFilms)
        runBlocking {
            datastore.getAll().mapLatest {
                Assert.assertEquals(it, films)
                coVerify { database.filmQueries }
                coVerify { queries.selectAll().asFlow().mapToList() }
            }.launchIn(this)
        }
    }

    @Test
    fun testAddFilm() {
        val film = Film("id", "title", "description", "date", "director", "imageURL", false)
        coEvery {
            queries.insert(
                film.id,
                film.title,
                film.description,
                film.releaseDate,
                film.director,
                film.imageURL,
                false
            )
        } just Runs
        datastore.add(film)
        coVerify { database.filmQueries }
        coVerify {
            queries.insert(
                film.id,
                film.title,
                film.description,
                film.releaseDate,
                film.director,
                film.imageURL,
                false
            )
        }
    }

    @Test
    fun testRemoveFilm() {
        val id = "id"
        coEvery { queries.delete(id) } just Runs
        datastore.remove(id)
        coVerify { database.filmQueries }
        coVerify { queries.delete(id) }
    }

    @Test
    fun testUpdateWatchedFilm() {
        val id = "id"
        coEvery { queries.updateWatched(true, id) } just Runs
        coEvery { queries.updateWatched(false, id) } just Runs
        datastore.updateWatched(true, id)
        datastore.updateWatched(false, id)
        coVerify { database.filmQueries }
        coVerifySequence {
            queries.updateWatched(true, id)
            queries.updateWatched(false, id)
        }
    }
}
