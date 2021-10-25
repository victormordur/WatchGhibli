package com.victormordur.gihbli.app.domain.usecase

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.simple.AddToUser
import com.victormordur.gihbli.app.domain.usecase.simple.MarkUserToBeWatched
import com.victormordur.gihbli.app.domain.usecase.simple.MarkUserWatched
import com.victormordur.gihbli.app.domain.usecase.simple.RemoveFromUser
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

class FilmSimpleUseCasesTest {
    private val repository: FilmRepositoryContract = mockk()
    private val addToUser = AddToUser(repository)
    private val removeFromUser = RemoveFromUser(repository)
    private val markUserToBeWatched = MarkUserToBeWatched(repository)
    private val markUserWatched = MarkUserWatched(repository)

    @After
    fun tearDown() {
        confirmVerified(repository)
    }

    @Test
    fun testAddToUser() {
        val film = Film("id", "title", "description", "date", "director", "imageURL", false)
        coEvery { repository.addToUser(film) } just Runs
        runBlocking {
            addToUser.execute(film)
        }
        coVerify { repository.addToUser(film) }
    }

    @Test
    fun testRemoveFromUser() {
        val id = "id"
        coEvery { repository.removeFromUser(id) } just Runs
        runBlocking {
            removeFromUser.execute(id)
        }
        coVerify { repository.removeFromUser(id) }
    }

    @Test
    fun testMarkUserToBeWatched() {
        val id = "id"
        coEvery { repository.markToBeWatched(id) } just Runs
        runBlocking {
            markUserToBeWatched.execute(id)
        }
        coVerify { repository.markToBeWatched(id) }
    }

    @Test
    fun testMarkUserWatched() {
        val id = "id"
        coEvery { repository.markWatched(id) } just Runs
        runBlocking {
            markUserWatched.execute(id)
        }
        coVerify { repository.markWatched(id) }
    }
}
