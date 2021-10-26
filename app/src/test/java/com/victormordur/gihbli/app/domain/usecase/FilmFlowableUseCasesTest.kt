package com.victormordur.gihbli.app.domain.usecase

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test

class FilmFlowableUseCasesTest {
    private val repository: FilmRepositoryContract = mockk()
    private val getCatalogueFiltered = GetCatalogueFilteredByUserFilms(repository)
    private val getUserToBeWatched = GetUserToBeWatchedFilms(repository)
    private val getUserWatched = GetUserWatchedFilms(repository)

    private val filmsCatalogue = listOf(1, 2, 3, 4, 5).map {
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

    private val filmsUser = filmsCatalogue.subList(2, 5)

    private val filmsUserSomeWatched = filmsUser.mapIndexed { index, film ->
        if (index % 2 == 0) {
            film.copy(watched = true)
        } else {
            film
        }
    }

    @After
    fun tearDown() {
        confirmVerified(repository)
    }

    @Test
    fun testCatalogueFilteredByUser() {
        val flowCatalogue = flowOf(filmsCatalogue)
        val flowUser = flowOf(filmsUser)
        coEvery { repository.getCatalogueFilms() } returns flowCatalogue
        coEvery { repository.getUserFilms() } returns flowUser
        runBlocking {
            getCatalogueFiltered.requestFlow().mapLatest {
                Assert.assertEquals(it, filmsCatalogue.subList(0, 2))
                coVerify { repository.getCatalogueFilms() }
                coVerify { repository.getUserFilms() }
            }.launchIn(this)
        }
    }

    @Test
    fun testUserToBeWatched() {
        val flowUser = flowOf(filmsUserSomeWatched)
        coEvery { repository.getUserFilms() } returns flowUser
        runBlocking {
            getUserToBeWatched.requestFlow().mapLatest {
                Assert.assertEquals(it, listOf(filmsUserSomeWatched[1]))
                coVerify { repository.getUserFilms() }
            }.launchIn(this)
        }
    }

    @Test
    fun testUserWatched() {
        val flowUser = flowOf(filmsUserSomeWatched)
        coEvery { repository.getUserFilms() } returns flowUser
        runBlocking {
            getUserWatched.requestFlow().mapLatest {
                Assert.assertEquals(it, listOf(filmsUserSomeWatched[0], filmsUserSomeWatched[2]))
                coVerify { repository.getUserFilms() }
            }.launchIn(this)
        }
    }
}
