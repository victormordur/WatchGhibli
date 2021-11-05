package com.victormordur.gihbli.app.presentation.view.composable

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.victormordur.gihbli.app.presentation.list.navigation.FilmListNavigationItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        FilmListNavigationItem.Catalogue,
        FilmListNavigationItem.WatchList,
        FilmListNavigationItem.Watched
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(item.iconResId),
                        contentDescription = stringResource(item.labelResId)
                    )
                },
                label = { Text(text = stringResource(item.labelResId)) },
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
