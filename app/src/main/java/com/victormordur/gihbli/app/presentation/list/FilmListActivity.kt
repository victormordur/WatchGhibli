package com.victormordur.gihbli.app.presentation.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.ViewState
import com.victormordur.gihbli.app.presentation.style.Dimensions
import com.victormordur.gihbli.app.presentation.style.GhibliTheme
import com.victormordur.gihbli.app.presentation.view.composable.ErrorSnackBar
import com.victormordur.gihbli.app.presentation.view.composable.NetworkImage
import com.victormordur.gihbli.app.presentation.view.composable.Toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListActivity : ComponentActivity() {

    private val viewModel: FilmListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GhibliTheme {
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    topBar = {
                        Toolbar(title = R.string.app_name)
                    },
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        SnackbarHost(it) { data ->
                            ErrorSnackBar(data = data)
                        }
                    }
                ) {
                    CatalogueContent(
                        viewModel = viewModel,
                        onItemClick = { showInfoSnackBar(it) },
                        scaffoldState = scaffoldState,
                        lifecycleScope = lifecycleScope
                    )
                }
            }
        }
    }
}

private fun showInfoSnackBar(film: Film) {
    // Show info snackbar
}

@Composable
private fun CatalogueContent(
    viewModel: FilmListViewModel,
    onItemClick: (Film) -> Unit,
    scaffoldState: ScaffoldState,
    lifecycleScope: LifecycleCoroutineScope
) {
    val content = viewModel.catalogueContent.collectAsState(lifecycleScope.coroutineContext).value

    // TODO handle actions errors here and show

    val swipeRefreshState = rememberSwipeRefreshState(content is ViewState.Loading)

    when (content) {
        is ViewState.Content -> {
            // TODO Check if list and show empty content if necessary
            CatalogueList(
                swipeRefreshState,
                content.data,
                viewModel::refreshCatalogue,
                onItemClick
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

@Composable
private fun CatalogueList(
    swipeRefreshState: SwipeRefreshState,
    filmList: List<Film>,
    onRefresh: () -> Unit,
    onItemClick: (Film) -> Unit
) {
    SwipeRefresh(state = swipeRefreshState, onRefresh = { onRefresh.invoke() }) {
    LazyColumn(
        modifier = Modifier.padding(top = Dimensions.Margin.double()),
    ) {
        items(filmList) { film ->
            CatalogueListItem(film, onItemClick)
        }
    }
}
}

@Composable
private fun CatalogueListItem(film: Film, onItemClick: (Film) -> Unit) {
    Card(
        shape = RoundedCornerShape(Dimensions.Radius.card()),
        modifier = Modifier
            .padding(Dimensions.Margin.default())
            .height(Dimensions.Card.totalHeight())
    ) {
        Box {
            NetworkImage(url = film.imageURL)
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.secondary)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = Dimensions.Margin.double())
                    .padding(vertical = Dimensions.Margin.double())
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
                        onClick = { },
                        modifier = Modifier.background(
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_dark),
                            contentDescription = stringResource(id = R.string.add_to_watch_list_description),
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            }
        }
    }
}
