package com.victormordur.gihbli.app.presentation.view.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import com.victormordur.gihbli.app.presentation.style.Dimensions

@Composable
fun Image(@DrawableRes drawableRes: Int, modifier: Modifier = Modifier.fillMaxWidth()) {
    androidx.compose.foundation.Image(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun NetworkImage(url: String) =
    androidx.compose.foundation.Image(
        modifier = Modifier.aspectRatio(Dimensions.IMAGE_RATIO_16_9),
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
            }
        ),
        contentScale = ContentScale.FillWidth,
        contentDescription = null
    )
