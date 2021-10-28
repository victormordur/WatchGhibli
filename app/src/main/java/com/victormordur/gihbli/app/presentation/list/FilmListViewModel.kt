package com.victormordur.gihbli.app.presentation.list

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.presentation.ViewState
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

    fun refreshCatalogue() = launchHandledAction {
        repository.refreshCatalogueFilms()
    }

    fun addFilmToWatchList(film: Film) = launchHandledAction {
        repository.addToUser(film)
    }

    fun removeFilmFromWatchList(film: Film) = launchHandledAction {
        repository.removeFromUser(film.id)
    }

    fun moveFilmBackAsToBeWatched(film: Film) = launchHandledAction {
        repository.markToBeWatched(film.id)
    }

    fun moveFilmForwardAsWatched(film: Film) = launchHandledAction {
        repository.markWatched(film.id)
    }
}
