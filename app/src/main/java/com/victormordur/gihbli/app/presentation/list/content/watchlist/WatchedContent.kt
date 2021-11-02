package com.victormordur.gihbli.app.presentation.list.content.watchlist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.list.FilmListViewModel
import com.victormordur.gihbli.app.presentation.list.content.item.FilmListItemButtonConfig
import com.victormordur.gihbli.app.presentation.list.content.item.FilmListItemTwoButtonContent
import com.victormordur.gihbli.app.presentation.style.Dimensions
import com.victormordur.gihbli.app.presentation.view.composable.CircularProgressBar

@Composable
fun WatchedContent(
    viewModel: FilmListViewModel,
    onItemClick: (Film) -> Unit,
    onItemRemove: (Film) -> Unit,
    onItemMarkToBeWatched: (Film) -> Unit,
    lifecycleScope: LifecycleCoroutineScope
) {
    val content = viewModel.watchedContent.collectAsState(lifecycleScope.coroutineContext).value

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
        modifier = Modifier.padding(top = Dimensions.Margin.double()),
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
