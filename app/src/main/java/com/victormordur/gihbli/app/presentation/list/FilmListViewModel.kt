package com.victormordur.gihbli.app.presentation.list

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.usecase.flowable.GetCatalogueFilteredByUserFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserToBeWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.flowable.GetUserWatchedFilms
import com.victormordur.gihbli.app.domain.usecase.simple.AddToUser
import com.victormordur.gihbli.app.domain.usecase.simple.MarkUserToBeWatched
import com.victormordur.gihbli.app.domain.usecase.simple.MarkUserWatched
import com.victormordur.gihbli.app.domain.usecase.simple.RemoveFromUser
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.common.CommonViewModel
import kotlinx.coroutines.flow.StateFlow

@SuppressWarnings("LongParameterList")
class FilmListViewModel(
    getCatalogueFilteredByUserFilms: GetCatalogueFilteredByUserFilms,
    getUserToBeWatchedFilms: GetUserToBeWatchedFilms,
    getUserWatchedFilms: GetUserWatchedFilms,
    private val addToUser: AddToUser,
    private val removeFromUser: RemoveFromUser,
    private val markUserToBeWatched: MarkUserToBeWatched,
    private val markUserWatched: MarkUserWatched
) :
    CommonViewModel() {

    val catalogueContent: StateFlow<ViewState<List<Film>>> =
        getCatalogueFilteredByUserFilms.requestFlow().asViewStateFlow()
    val toBeWatchedContent: StateFlow<ViewState<List<Film>>> =
        getUserToBeWatchedFilms.requestFlow().asViewStateFlow()
    val watchedContent: StateFlow<ViewState<List<Film>>> =
        getUserWatchedFilms.requestFlow().asViewStateFlow()

    fun refreshCatalogue() {
        // TODO
    }

    fun addFilmToWatchList(film: Film) = addToUser.runWith(film)
    fun removeFilmFromWatchList(film: Film) = removeFromUser.runWith(film.id)
    fun moveFilmBackAsToBeWatched(film: Film) = markUserToBeWatched.runWith(film.id)
    fun moveFilmForwardAsWatched(film: Film) = markUserWatched.runWith(film.id)
}
