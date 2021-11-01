package com.victormordur.gihbli.app.presentation.style

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.victormordur.gihbli.app.presentation.style.Styles.ghibliDarkColorPalette
import com.victormordur.gihbli.app.presentation.style.Styles.ghibliLightColorPalette

object Styles {
    @SuppressLint("ConflictingOnColor")
    @Composable
    fun ghibliLightColorPalette() = lightColors(
        primary = Colors.primary(),
        primaryVariant = Colors.accent(),
        secondary = Colors.quartz(),
        background = Colors.primary(),
        onPrimary = Color.Black,
        error = Colors.error(),
    )

    @SuppressLint("ConflictingOnColor")
    @Composable
    fun ghibliDarkColorPalette() = darkColors(
        primary = Colors.primaryDark(),
        primaryVariant = Colors.accent(),
        secondary = Colors.violet(),
        background = Colors.primaryDark(),
        onPrimary = Color.White,
        error = Colors.error(),
    )
}

@Composable
fun GhibliTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        ghibliDarkColorPalette()
    } else {
        ghibliLightColorPalette()
    }

    MaterialTheme(
        colors = colors,
        content = content,
    )
}
