package com.victormordur.gihbli.app.presentation.list.content.catalogue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.list.content.empty.EmptyListContent
import com.victormordur.gihbli.app.presentation.list.content.error.ErrorListContent
import com.victormordur.gihbli.app.presentation.list.content.item.FilmListItemButtonConfig
import com.victormordur.gihbli.app.presentation.list.content.item.FilmListItemOneButtonContent
import com.victormordur.gihbli.app.presentation.style.Dimensions
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CatalogueContent(
    catalogueContent: StateFlow<ViewState<List<Film>>>,
    onRefresh: () -> Unit,
    onItemClick: (Film) -> Unit,
    onItemAdd: (Film) -> Unit,
    lifecycleScope: LifecycleCoroutineScope
) {
    val content = catalogueContent.collectAsState(lifecycleScope.coroutineContext).value

    val swipeRefreshState = rememberSwipeRefreshState(content is ViewState.Loading)

    SwipeRefresh(state = swipeRefreshState, onRefresh = { onRefresh.invoke() }) {
    when (content) {
        is ViewState.Content -> {
            if (content.data.isNotEmpty()) {
                CatalogueList(
                    content.data,
                    onItemClick,
                    onItemAdd
                )
            } else {
                EmptyListContent(R.string.catalogue_list_empty)
            }
        }
        is ViewState.Loading -> {
            // Nothing to do here.
            // Managed by SwipeRefreshSate
        }
        is ViewState.Error -> {
            ErrorListContent()
        }
    }
}
}

@Composable
private fun CatalogueList(
    filmList: List<Film>,
    onItemClick: (Film) -> Unit,
    onItemAdd: (Film) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Dimensions.Margin.default())
    ) {
        items(filmList) { film ->
            CatalogueListItem(film, onItemClick, onItemAdd)
        }
    }
}

@Composable
private fun CatalogueListItem(film: Film, onItemClick: (Film) -> Unit, onItemAdd: (Film) -> Unit) {
    FilmListItemOneButtonContent(
        film = film,
        onItemClick = onItemClick,
        buttonConfig = FilmListItemButtonConfig(
            R.drawable.ic_add_dark,
            R.string.add_to_watch_list_desc,
            onItemAdd
        )
    )
}
