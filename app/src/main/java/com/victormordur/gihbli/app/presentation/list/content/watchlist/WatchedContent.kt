package com.victormordur.gihbli.app.presentation.list.content.watchlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LifecycleCoroutineScope
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.list.content.item.FilmListItemButtonConfig
import com.victormordur.gihbli.app.presentation.list.content.item.FilmListItemTwoButtonContent
import com.victormordur.gihbli.app.presentation.style.Dimensions
import com.victormordur.gihbli.app.presentation.view.composable.CircularProgressBar
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WatchedContent(
    watchedContent: StateFlow<ViewState<List<Film>>>,
    onItemClick: (Film) -> Unit,
    onItemRemove: (Film) -> Unit,
    onItemMarkToBeWatched: (Film) -> Unit,
    lifecycleScope: LifecycleCoroutineScope
) {
    val content = watchedContent.collectAsState(lifecycleScope.coroutineContext).value

    when (content) {
        is ViewState.Content -> {
            // TODO Check if list and show empty content if necessary
            WatchedList(
                content.data,
                onItemClick,
                onItemRemove,
                onItemMarkToBeWatched
            )
        }
        is ViewState.Loading -> {
            CircularProgressBar()
        }
        is ViewState.Error -> {
            // TODO Launch error content
        }
    }
}

@Composable
private fun WatchedList(
    filmList: List<Film>,
    onItemClick: (Film) -> Unit,
    onItemRemove: (Film) -> Unit,
    onItemMarkToBeWatched: (Film) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Dimensions.Margin.default())
    ) {
        items(filmList) { film ->
            WatchedListItem(film, onItemClick, onItemRemove, onItemMarkToBeWatched)
        }
    }
}

@Composable
private fun WatchedListItem(
    film: Film,
    onItemClick: (Film) -> Unit,
    onItemRemove: (Film) -> Unit,
    onItemMarkToBeWatched: (Film) -> Unit
) {
    FilmListItemTwoButtonContent(
        film = film,
        onItemClick = onItemClick,
        rightButtonConfig = FilmListItemButtonConfig(
            R.drawable.ic_unwatch_dark,
            R.string.mark_to_be_watched_desc,
            onItemMarkToBeWatched
        ),
        leftButtonConfig = FilmListItemButtonConfig(
            R.drawable.ic_remove_dark,
            R.string.remove_from_watch_list_desc,
            onItemRemove
        )
    )
}
