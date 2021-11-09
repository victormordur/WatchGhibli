package com.victormordur.gihbli.app.presentation.list

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.common.ActionResultContract
import kotlinx.coroutines.flow.StateFlow

interface FilmListContract {
    interface Content {
        val catalogueContent: StateFlow<ViewState<List<Film>>>
        val toBeWatchedContent: StateFlow<ViewState<List<Film>>>
        val watchedContent: StateFlow<ViewState<List<Film>>>
    }

    interface Actions : ActionResultContract {
        fun refreshCatalogue()
        fun addFilmToWatchList(film: Film)
        fun removeFilmFromWatchList(film: Film)
        fun moveFilmBackAsToBeWatched(film: Film)
        fun moveFilmForwardAsWatched(film: Film)
    }
}
