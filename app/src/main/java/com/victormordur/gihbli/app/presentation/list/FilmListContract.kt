package com.victormordur.gihbli.app.presentation.list

import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.presentation.ViewState
import kotlinx.coroutines.flow.StateFlow

interface FilmListContract {
    interface Content {
        val catalogueContent: StateFlow<ViewState<List<Film>>>
        val toBeWatchedContent: StateFlow<ViewState<List<Film>>>
        val watchedContent: StateFlow<ViewState<List<Film>>>
    }
    interface Actions {
        fun refreshCatalogue()
        fun addFilmToWatchList(film: Film)
        fun removeFilmFromWatchList(film: Film)
        fun moveFilmBackAsToBeWatched(film: Film)
        fun moveFilmForwardAsWatched(film: Film)
    }
}
