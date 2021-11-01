package com.victormordur.gihbli.app.presentation.list

import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.common.ActionResult
import com.victormordur.gihbli.app.presentation.common.CommonViewModel
import kotlinx.coroutines.flow.StateFlow

class FilmListViewModel(
    private val repository: FilmRepositoryContract,
    getCatalogueFilteredByUserFilms: GetCatalogueFilteredByUserFilms,
    getUserToBeWatchedFilms: GetUserToBeWatchedFilms,
    getUserWatchedFilms: GetUserWatchedFilms
) :
    CommonViewModel() {

    val catalogueContent: StateFlow<ViewState<List<Film>>> =
        getCatalogueFilteredByUserFilms.requestFlow().asViewStateFlow()

    val toBeWatchedContent: StateFlow<ViewState<List<Film>>> =
        getUserToBeWatchedFilms.requestFlow().asViewStateFlow()

    val watchedContent: StateFlow<ViewState<List<Film>>> =
        getUserWatchedFilms.requestFlow().asViewStateFlow()

    fun refreshCatalogue() = launchHandledActionNoResult {
        repository.refreshCatalogueFilms()
    }

    fun addFilmToWatchList(film: Film) = launchHandledAction {
        repository.addToUser(film)
        FilmListActionSuccess.AddFilm(film)
    }

    fun removeFilmFromWatchList(film: Film) = launchHandledAction {
        repository.removeFromUser(film.id)
        FilmListActionSuccess.RemoveFilm(film)
    }

    fun moveFilmBackAsToBeWatched(film: Film) = launchHandledAction {
        repository.markToBeWatched(film.id)
        FilmListActionSuccess.MarkToBeWatched(film)
    }

    fun moveFilmForwardAsWatched(film: Film) = launchHandledAction {
        repository.markWatched(film.id)
        FilmListActionSuccess.MarkWatched(film)
    }
}

sealed class FilmListActionSuccess(override val messageId: Int) : ActionResult {
    abstract val film: Film

    data class AddFilm(override val film: Film) :
        FilmListActionSuccess(R.string.add_film_success)

    data class RemoveFilm(override val film: Film) :
        FilmListActionSuccess(R.string.remove_film_success)

    data class MarkToBeWatched(override val film: Film) :
        FilmListActionSuccess(R.string.mark_to_be_watched_success)

    data class MarkWatched(override val film: Film) :
        FilmListActionSuccess(R.string.mark_watched_success)
}
