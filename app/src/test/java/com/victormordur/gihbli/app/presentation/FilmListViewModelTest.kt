package com.victormordur.gihbli.app.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepository
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.presentation.list.FilmListActionSuccess
import com.victormordur.gihbli.app.presentation.list.FilmListViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FilmListViewModelTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val repository: FilmRepository = mockk()
    private val getCatalogueFiltered: GetCatalogueFilteredByUserFilms = mockk()
    private val getUserToBeWatched: GetUserToBeWatchedFilms = mockk()
    private val getUserWatched: GetUserWatchedFilms = mockk()

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

    private val filmsToBeWatched = filmsCatalogue.subList(2, 4)
    private val filmsWatched = filmsCatalogue.subList(4, 5)

    private val flowCatalogue = flowOf(filmsCatalogue)
    private val flowToBeWatched = flowOf(filmsToBeWatched)
    private val flowWatched = flowOf(filmsWatched)

    private val film = Film("id", "title", "description", "date", "director", "imageURL", false)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        coEvery { getCatalogueFiltered.requestFlow() } returns flowCatalogue
        coEvery { getUserToBeWatched.requestFlow() } returns flowToBeWatched
        coEvery { getUserWatched.requestFlow() } returns flowWatched
    }

    @After
    fun tearDown() {
        coVerify { getCatalogueFiltered.requestFlow() }
        coVerify { getUserToBeWatched.requestFlow() }
        coVerify { getUserWatched.requestFlow() }
        confirmVerified(
            repository,
            getCatalogueFiltered,
            getUserToBeWatched,
            getUserWatched,
        )
        Dispatchers.resetMain()
    }

    @Test
    fun testCatalogueContent() = testCoroutineDispatcher.runBlockingTest {
        val viewModel = initViewModel()
        viewModel.catalogueContent.mapLatest {
            val result = (it as ViewState.Content).data
            Assert.assertEquals(result, filmsCatalogue)
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        testCoroutineDispatcher.advanceUntilIdle()
    }

    @Test
    fun testToBeWatchedContent() = testCoroutineDispatcher.runBlockingTest {
        val viewModel = initViewModel()
        viewModel.toBeWatchedContent.mapLatest {
            val result = (it as ViewState.Content).data
            Assert.assertEquals(result, filmsToBeWatched)
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        testCoroutineDispatcher.advanceUntilIdle()
    }

    @Test
    fun testWatchedContent() = testCoroutineDispatcher.runBlockingTest {
        val viewModel = initViewModel()
        viewModel.watchedContent.mapLatest {
            val result = (it as ViewState.Content).data
            Assert.assertEquals(result, filmsWatched)
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        testCoroutineDispatcher.advanceUntilIdle()
    }

    @Test
    fun testRefreshCatalogFilms() {
        coEvery { repository.refreshCatalogueFilms() } just Runs
        val viewModel = initViewModel()
        runBlocking {
            viewModel.refreshCatalogue()
        }
        coVerify { repository.refreshCatalogueFilms() }
    }

    @Test
    fun testAddFilmToWatchList() = testCoroutineDispatcher.runBlockingTest {
        coEvery { repository.addToUser(film) } just Runs
        val viewModel = initViewModel()
        viewModel.actionResultFlow.mapLatest {
            Assert.assertEquals(it, FilmListActionSuccess.AddFilm(film))
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        viewModel.addFilmToWatchList(film)
        testCoroutineDispatcher.advanceUntilIdle()
        coVerify { repository.addToUser(film) }
    }

    @Test
    fun testRemoveFilmFromWatchList() = testCoroutineDispatcher.runBlockingTest {
        coEvery { repository.removeFromUser(film.id) } just Runs
        val viewModel = initViewModel()
        viewModel.actionResultFlow.mapLatest {
            Assert.assertEquals(it, FilmListActionSuccess.RemoveFilm(film))
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        viewModel.removeFilmFromWatchList(film)
        coVerify { repository.removeFromUser(film.id) }
    }

    @Test
    fun testMoveFilmBackAsToBeWatched() = testCoroutineDispatcher.runBlockingTest {
        coEvery { repository.markToBeWatched(film.id) } just Runs
        val viewModel = initViewModel()
        viewModel.actionResultFlow.mapLatest {
            Assert.assertEquals(it, FilmListActionSuccess.MarkToBeWatched(film))
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        viewModel.moveFilmBackAsToBeWatched(film)
        coVerify { repository.markToBeWatched(film.id) }
    }

    @Test
    fun testMoveFilmForwardAsWatched() = testCoroutineDispatcher.runBlockingTest {
        coEvery { repository.markWatched(film.id) } just Runs
        val viewModel = initViewModel()
        viewModel.actionResultFlow.mapLatest {
            Assert.assertEquals(it, FilmListActionSuccess.MarkWatched(film))
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        viewModel.moveFilmForwardAsWatched(film)
        coVerify { repository.markWatched(film.id) }
    }

    @Test
    fun testActionFailureNotified() = testCoroutineDispatcher.runBlockingTest {
        val exception = Exception("Dummy Exception")
        coEvery { repository.addToUser(film) } throws exception
        val viewModel = initViewModel()
        viewModel.actionErrorFlow.mapLatest {
            Assert.assertEquals(it, exception)
        }.launchIn(CoroutineScope(testCoroutineDispatcher))
        viewModel.addFilmToWatchList(film)
        testCoroutineDispatcher.advanceUntilIdle()
        coVerify { repository.addToUser(film) }
    }

    private fun initViewModel() = FilmListViewModel(
        repository,
        getCatalogueFiltered,
        getUserToBeWatched,
        getUserWatched,
    )
}
