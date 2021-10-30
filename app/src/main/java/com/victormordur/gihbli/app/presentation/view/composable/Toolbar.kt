package com.victormordur.gihbli.app.presentation.view.composable

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun Toolbar(
    @StringRes title: Int? = null,
    color: Color = MaterialTheme.colors.secondary,
) {
    TopAppBar(
        title = {
            title?.let {
                Text(
                    stringResource(id = title),
                    style = MaterialTheme.typography.h6
                )
            }
        },
        backgroundColor = color,
        elevation = 0.dp,
    )
}
