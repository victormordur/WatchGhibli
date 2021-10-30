package com.victormordur.gihbli.app.presentation.view.composable

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

enum class SnackBarType { Info, Error }

@Composable
fun InfoSnackBar(data: SnackbarData) {
    TextSnackBar(backgroundColor = MaterialTheme.colors.primaryVariant, snackBarData = data)
}

@Composable
fun ErrorSnackBar(data: SnackbarData) {
    TextSnackBar(backgroundColor = MaterialTheme.colors.error, snackBarData = data)
}

@Composable
fun TextSnackBar(backgroundColor: Color, snackBarData: SnackbarData) {
    Snackbar(
        backgroundColor = backgroundColor,
        shape = RectangleShape,
    ) {
        Text(
            text = snackBarData.message,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.body2,
        )
    }
}
