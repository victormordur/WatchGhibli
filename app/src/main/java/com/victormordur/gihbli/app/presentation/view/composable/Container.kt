package com.victormordur.gihbli.app.presentation.view.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.victormordur.gihbli.app.presentation.style.Dimensions

@Composable
fun Space(
    size: Dp = Dimensions.Margin.large(),
) = Spacer(Modifier.size(size))
