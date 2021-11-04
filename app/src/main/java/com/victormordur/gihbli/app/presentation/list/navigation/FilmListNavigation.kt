package com.victormordur.gihbli.app.presentation.list.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.list.FilmListContract
import com.victormordur.gihbli.app.presentation.list.content.catalogue.CatalogueContent
import com.victormordur.gihbli.app.presentation.list.content.watched.WatchListContent
import com.victormordur.gihbli.app.presentation.list.content.watchlist.WatchedContent

@Composable
fun FilmListNavigation(
    navController: NavHostController,
    content: FilmListContract.Content,
    actions: FilmListContract.Actions,
    onItemClick: (Film) -> Unit,
    lifecycleScope: LifecycleCoroutineScope
) {
    NavHost(navController, startDestination = FilmListNavigationItem.Catalogue.route) {
        composable(FilmListNavigationItem.Catalogue.route) {
            CatalogueContent(
                catalogueContent = content.catalogueContent,
                onRefresh = { actions.refreshCatalogue() },
                onItemClick = onItemClick,
                onItemAdd = { actions.addFilmToWatchList(it) },
                lifecycleScope = lifecycleScope
            )
        }
        composable(FilmListNavigationItem.WatchList.route) {
            WatchListContent(
                toBeWatchedContent = content.toBeWatchedContent,
                onItemClick = onItemClick,
                onItemRemove = { actions.removeFilmFromWatchList(it) },
                onItemMarkWatched = { actions.moveFilmForwardAsWatched(it) },
                lifecycleScope = lifecycleScope
            )
        }
        composable(FilmListNavigationItem.Watched.route) {
            WatchedContent(
                watchedContent = content.watchedContent,
                onItemClick = onItemClick,
                onItemRemove = { actions.removeFilmFromWatchList(it) },
                onItemMarkToBeWatched = { actions.moveFilmBackAsToBeWatched(it) },
                lifecycleScope = lifecycleScope
            )
        }
    }
}
