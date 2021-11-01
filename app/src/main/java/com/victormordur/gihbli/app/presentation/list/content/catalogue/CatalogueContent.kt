package com.victormordur.gihbli.app.presentation.list.content.catalogue

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.list.FilmListViewModel
import com.victormordur.gihbli.app.presentation.style.Dimensions
import com.victormordur.gihbli.app.presentation.view.composable.NetworkImage

@Composable
fun CatalogueContent(
    viewModel: FilmListViewModel,
    onRefresh: () -> Unit,
    onItemClick: (Film) -> Unit,
    onItemAdd: (Film) -> Unit,
    lifecycleScope: LifecycleCoroutineScope
) {
    val content = viewModel.catalogueContent.collectAsState(lifecycleScope.coroutineContext).value

    val swipeRefreshState = rememberSwipeRefreshState(content is ViewState.Loading)

    SwipeRefresh(state = swipeRefreshState, onRefresh = { onRefresh.invoke() }) {
    when (content) {
        is ViewState.Content -> {
            // TODO Check if list and show empty content if necessary
            CatalogueList(
                content.data,
                onItemClick,
                onItemAdd
            )
        }
        is ViewState.Loading -> {
            // Nothing to do here.
            // Managed by SwipeRefreshSate
        }
        is ViewState.Error -> {
            // TODO Launch error content
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
        modifier = Modifier.padding(top = Dimensions.Margin.double()),
    ) {
        items(filmList) { film ->
            CatalogueListItem(film, onItemClick, onItemAdd)
        }
    }
}

@Composable
private fun CatalogueListItem(film: Film, onItemClick: (Film) -> Unit, onItemAdd: (Film) -> Unit) {
    Card(
        shape = RoundedCornerShape(Dimensions.Radius.card()),
        modifier = Modifier
            .padding(Dimensions.Margin.default())
            .height(Dimensions.Card.totalHeight())
            .clickable { onItemClick.invoke(film) }
    ) {
        Box {
            NetworkImage(url = film.imageURL)
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.secondary)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(Dimensions.Margin.double())
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(Dimensions.Card.titleWeight)
                ) {
                    Text(
                        text = film.title,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Normal
                    )
                }
                Column(
                    modifier = Modifier.weight(Dimensions.Card.buttonsWeight),
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = { onItemAdd.invoke(film) },
                        modifier = Modifier.background(
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_dark),
                            contentDescription = stringResource(id = R.string.add_to_watch_list_desc),
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            }
        }
    }
}
