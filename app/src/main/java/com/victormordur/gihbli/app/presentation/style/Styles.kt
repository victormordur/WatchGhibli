package com.victormordur.gihbli.app.presentation.style

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

object Styles {
    @Composable
    fun ghibliLightColorPalette() = lightColors(
        primary = Colors.primary(),
        primaryVariant = Colors.accent(),
        secondary = Colors.secondary(),
        background = Colors.primary(),
        // onPrimary = Colors.primaryDark(),
        error = Colors.error(),
    )

    @Composable
    fun ghibliDarkColorPalette() = darkColors(
        primary = Colors.primary(),
        primaryVariant = Colors.accent(),
        secondary = Colors.secondary(),
        background = Colors.primary(),
        // onPrimary = Colors.primaryDark(),
        error = Colors.error(),
    )
}
