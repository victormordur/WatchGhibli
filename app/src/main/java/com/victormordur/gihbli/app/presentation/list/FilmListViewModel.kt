package com.victormordur.gihbli.app.presentation.list

import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepository
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.common.ActionResult
import com.victormordur.gihbli.app.presentation.common.CommonViewModel
import kotlinx.coroutines.flow.StateFlow

class FilmListViewModel(
    private val repository: FilmRepository,
    getCatalogueFilteredByUserFilms: GetCatalogueFilteredByUserFilms,
    getUserToBeWatchedFilms: GetUserToBeWatchedFilms,
    getUserWatchedFilms: GetUserWatchedFilms
) :
    CommonViewModel(), FilmListContract.Content, FilmListContract.Actions {

    override val catalogueContent: StateFlow<ViewState<List<Film>>> =
        getCatalogueFilteredByUserFilms.requestFlow().asViewStateFlow()

    override val toBeWatchedContent: StateFlow<ViewState<List<Film>>> =
        getUserToBeWatchedFilms.requestFlow().asViewStateFlow()

    override val watchedContent: StateFlow<ViewState<List<Film>>> =
        getUserWatchedFilms.requestFlow().asViewStateFlow()

    override fun refreshCatalogue() = launchHandledActionNoResult {
        repository.refreshCatalogueFilms()
    }

    override fun addFilmToWatchList(film: Film) = launchHandledAction {
        repository.addToUser(film)
        FilmListActionSuccess.AddFilm(film)
    }

    override fun removeFilmFromWatchList(film: Film) = launchHandledAction {
        repository.removeFromUser(film.id)
        FilmListActionSuccess.RemoveFilm(film)
    }

    override fun moveFilmBackAsToBeWatched(film: Film) = launchHandledAction {
        repository.markToBeWatched(film.id)
        FilmListActionSuccess.MarkToBeWatched(film)
    }

    override fun moveFilmForwardAsWatched(film: Film) = launchHandledAction {
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
