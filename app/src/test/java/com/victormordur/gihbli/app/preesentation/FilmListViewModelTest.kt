package com.victormordur.gihbli.app.preesentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.simple.AddToUser
import com.victormordur.gihbli.app.domain.usecase.simple.MarkUserToBeWatched
import com.victormordur.gihbli.app.domain.usecase.simple.MarkUserWatched
import com.victormordur.gihbli.app.domain.usecase.simple.RemoveFromUser
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.list.FilmListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
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

    private val getCatalogueFiltered: GetCatalogueFilteredByUserFilms = mockk()
    private val getUserToBeWatched: GetUserToBeWatchedFilms = mockk()
    private val getUserWatched: GetUserWatchedFilms = mockk()
    private val addToUser: AddToUser = mockk()
    private val removeFromUser: RemoveFromUser = mockk()
    private val markUserToBeWatched: MarkUserToBeWatched = mockk()
    private val markUserWatched: MarkUserWatched = mockk()

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
            getCatalogueFiltered,
            getUserToBeWatched,
            getUserWatched,
            addToUser,
            removeFromUser,
            markUserToBeWatched,
            markUserWatched
        )
        Dispatchers.resetMain()
    }

    @Test
    fun testCatalogueContent() = testCoroutineDispatcher.runBlockingTest {
        val viewModel = initViewModel()
        runBlocking {
            viewModel.catalogueContent.mapLatest {
                val result = (it as ViewState.Content).data
                Assert.assertEquals(result, filmsCatalogue)
            }.launchIn(CoroutineScope(testCoroutineDispatcher))
        }
        testCoroutineDispatcher.advanceUntilIdle()
    }

    @Test
    fun testToBeWatchedContent() = testCoroutineDispatcher.runBlockingTest {
        val viewModel = initViewModel()
        runBlocking {
            viewModel.toBeWatchedContent.mapLatest {
                val result = (it as ViewState.Content).data
                Assert.assertEquals(result, filmsToBeWatched)
            }.launchIn(CoroutineScope(testCoroutineDispatcher))
        }
        testCoroutineDispatcher.advanceUntilIdle()
    }

    @Test
    fun testWatchedContent() = testCoroutineDispatcher.runBlockingTest {
        val viewModel = initViewModel()
        runBlocking {
            viewModel.watchedContent.mapLatest {
                val result = (it as ViewState.Content).data
                Assert.assertEquals(result, filmsWatched)
            }.launchIn(CoroutineScope(testCoroutineDispatcher))
        }
        testCoroutineDispatcher.advanceUntilIdle()
    }

    private fun initViewModel() = FilmListViewModel(
        getCatalogueFiltered,
        getUserToBeWatched,
        getUserWatched,
        addToUser,
        removeFromUser,
        markUserToBeWatched,
        markUserWatched
    )
}
