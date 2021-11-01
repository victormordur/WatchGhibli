package com.victormordur.gihbli.app.presentation.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.victormordur.gihbli.app.R

object Colors {
    @Composable
    fun primary(): Color = colorResource(R.color.colorPrimary)

    @Composable
    fun primaryDark(): Color = colorResource(R.color.colorPrimaryDark)

    @Composable
    fun accent(): Color = colorResource(R.color.colorAccent)

    @Composable
    fun secondary(): Color = colorResource(R.color.secondary)

    @Composable
    fun secondaryDark(): Color = colorResource(R.color.secondaryDark)

    @Composable
    fun error(): Color = colorResource(R.color.colorError)
}
