package com.victormordur.gihbli.app.presentation.list.content.watched

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LifecycleCoroutineScope
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.list.content.empty.EmptyListContent
import com.victormordur.gihbli.app.presentation.list.content.error.ErrorListContent
import com.victormordur.gihbli.app.presentation.list.content.item.FilmListItemButtonConfig
import com.victormordur.gihbli.app.presentation.list.content.item.FilmListItemTwoButtonContent
import com.victormordur.gihbli.app.presentation.style.Dimensions
import com.victormordur.gihbli.app.presentation.view.composable.CircularProgressBar
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WatchListContent(
    toBeWatchedContent: StateFlow<ViewState<List<Film>>>,
    onItemClick: (Film) -> Unit,
    onItemRemove: (Film) -> Unit,
    onItemMarkWatched: (Film) -> Unit,
    lifecycleScope: LifecycleCoroutineScope
) {
    val content = toBeWatchedContent.collectAsState(lifecycleScope.coroutineContext).value

    when (content) {
        is ViewState.Content -> {
            if (content.data.isNotEmpty()) {
                WatchListList(
                    content.data,
                    onItemClick,
                    onItemRemove,
                    onItemMarkWatched
                )
            } else {
                EmptyListContent(R.string.watch_list_list_empty)
            }
        }
        is ViewState.Loading -> {
            CircularProgressBar()
        }
        is ViewState.Error -> {
            ErrorListContent()
        }
    }
}

@Composable
private fun WatchListList(
    filmList: List<Film>,
    onItemClick: (Film) -> Unit,
    onItemRemove: (Film) -> Unit,
    onItemMarkWatched: (Film) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Dimensions.Margin.default())
    ) {
        items(filmList) { film ->
            WatchListListItem(film, onItemClick, onItemRemove, onItemMarkWatched)
        }
    }
}

@Composable
private fun WatchListListItem(
    film: Film,
    onItemClick: (Film) -> Unit,
    onItemRemove: (Film) -> Unit,
    onItemMarkWatched: (Film) -> Unit
) {
    FilmListItemTwoButtonContent(
        film = film,
        onItemClick = onItemClick,
        rightButtonConfig = FilmListItemButtonConfig(
            R.drawable.ic_watch_dark,
            R.string.mark_watched_desc,
            onItemMarkWatched
        ),
        leftButtonConfig = FilmListItemButtonConfig(
            R.drawable.ic_remove_dark,
            R.string.remove_from_watch_list_desc,
            onItemRemove
        )
    )
}
