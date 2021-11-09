package com.victormordur.gihbli.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.testing.TestLifecycleOwner
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.page.CataloguePage
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.common.ActionResult
import com.victormordur.gihbli.app.presentation.list.FilmListActivity
import com.victormordur.gihbli.app.presentation.list.FilmListContent
import com.victormordur.gihbli.app.presentation.list.FilmListContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Rule
import org.junit.Test

class FilmListScreenTest {
    private val testLifecycleOwner = TestLifecycleOwner()

    @get:Rule()
    var composeActivityTestRule = createAndroidComposeRule<FilmListActivity>()

    @Test
    fun testEmptyContents() {
        composeActivityTestRule.setContent {
            FilmListContent(
                contentContract = object : FilmListContract.Content {
                    override val catalogueContent: StateFlow<ViewState<List<Film>>> =
                        MutableStateFlow(ViewState.Content(emptyList()))
                    override val toBeWatchedContent: StateFlow<ViewState<List<Film>>> =
                        MutableStateFlow(ViewState.Content(emptyList()))
                    override val watchedContent: StateFlow<ViewState<List<Film>>> =
                        MutableStateFlow(ViewState.Content(emptyList()))
                },
                actionsContract = object : FilmListContract.Actions {
                    override fun refreshCatalogue() {
                    }

                    override fun addFilmToWatchList(film: Film) {
                    }

                    override fun removeFilmFromWatchList(film: Film) {
                    }

                    override fun moveFilmBackAsToBeWatched(film: Film) {
                    }

                    override fun moveFilmForwardAsWatched(film: Film) {
                    }

                    override val actionErrorFlow: Flow<Throwable> = emptyFlow()
                    override val actionResultFlow: Flow<ActionResult> = emptyFlow()
                },
                lifecycleScope = testLifecycleOwner.lifecycleScope
            )
        }
        val cataloguePage = CataloguePage(composeActivityTestRule)
        cataloguePage.assertIsEmpty()
        val watchListPage = cataloguePage.navigateToWatchList()
        watchListPage.assertIsEmpty()
        val watchedPage = watchListPage.navigateToWatched()
        watchedPage.assertIsEmpty()
    }
}
