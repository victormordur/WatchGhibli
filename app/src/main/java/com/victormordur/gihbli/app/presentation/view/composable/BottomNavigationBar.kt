package com.victormordur.gihbli.app.presentation.view.composable

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.victormordur.gihbli.app.presentation.list.navigation.NavigationItem

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.Catalogue,
        NavigationItem.WatchList,
        NavigationItem.Watched
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
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
                    /* TODO */
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}