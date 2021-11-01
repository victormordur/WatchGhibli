package com.victormordur.gihbli.app.presentation.view.composable

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun InfoSnackBar(data: SnackbarData) {
    TextSnackBar(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        Color.Black,
        snackBarData = data
    )
}

@Composable
fun ErrorSnackBar(data: SnackbarData) {
    TextSnackBar(
        backgroundColor = MaterialTheme.colors.error,
        Color.White,
        snackBarData = data
    )
}

@Composable
fun TextSnackBar(backgroundColor: Color, textColor: Color, snackBarData: SnackbarData) {
    Snackbar(
        backgroundColor = backgroundColor,
        modifier = Modifier
    ) {
        Text(
            text = snackBarData.message,
            color = textColor,
            style = MaterialTheme.typography.body1,
        )
    }
}

enum class SnackBarType { Info, Error }
