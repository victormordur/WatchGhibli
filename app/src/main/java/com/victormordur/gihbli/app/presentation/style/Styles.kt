package com.victormordur.gihbli.app.presentation.style

import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

object Styles {
    @Composable
    fun ghibliColorPalette() = lightColors(
        primary = Colors.primary(),
        primaryVariant = Colors.accent(),
        secondary = Colors.secondary(),
        background = Colors.secondary(),
        onPrimary = Colors.primaryDark(),
        error = Colors.error(),
    )
}
