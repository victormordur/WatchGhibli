package com.victormordur.gihbli.app.presentation.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.victormordur.gihbli.app.domain.model.Film

@Composable
fun FilmListItemButton(
    film: Film,
    onClick: (Film) -> Unit,
    imageResId: Int,
    descriptionResId: Int,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = { onClick.invoke(film) },
        modifier = Modifier.background(
            color = MaterialTheme.colors.primary,
            shape = CircleShape
        ) then modifier
    ) {
        Icon(
            painter = painterResource(id = imageResId),
            contentDescription = stringResource(id = descriptionResId),
            tint = MaterialTheme.colors.primaryVariant
        )
    }
}
