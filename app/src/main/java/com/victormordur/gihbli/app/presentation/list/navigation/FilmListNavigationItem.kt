package com.victormordur.gihbli.app.presentation.list.navigation

import com.victormordur.gihbli.app.R

sealed class FilmListNavigationItem(val route: String, val labelResId: Int, val iconResId: Int) {
    object Catalogue :
        FilmListNavigationItem("catalogue", R.string.catalogue, R.drawable.ic_catalogue_dark)
    object WatchList :
        FilmListNavigationItem("watchlist", R.string.watch_list, R.drawable.ic_watch_list_dark)
    object Watched :
        FilmListNavigationItem("watched", R.string.watched, R.drawable.ic_watch_dark)
}
