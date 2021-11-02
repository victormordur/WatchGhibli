package com.victormordur.gihbli.app.presentation.list.content.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.presentation.style.Dimensions
import com.victormordur.gihbli.app.presentation.view.composable.FilmListCard
import com.victormordur.gihbli.app.presentation.view.composable.FilmListItemButton
import com.victormordur.gihbli.app.presentation.view.composable.Space

@Composable
fun FilmListItemOneButtonContent(
    film: Film,
    onItemClick: (Film) -> Unit,
    buttonConfig: FilmListItemButtonConfig
) {
    FilmListItemContent(
        film = film,
        onItemClick = onItemClick
    ) {
        FilmListItemButton(
            film = film,
            onClick = buttonConfig.onClick,
            imageResId = buttonConfig.imageResId,
            descriptionResId = buttonConfig.descriptionResId
        )
    }
}

@Composable
fun FilmListItemTwoButtonContent(
    film: Film,
    onItemClick: (Film) -> Unit,
    rightButtonConfig: FilmListItemButtonConfig,
    leftButtonConfig: FilmListItemButtonConfig
) {
    FilmListItemContent(
        film = film,
        onItemClick = onItemClick
    ) {
        FilmListItemButton(
            film = film,
            onClick = leftButtonConfig.onClick,
            imageResId = leftButtonConfig.imageResId,
            descriptionResId = leftButtonConfig.descriptionResId
        )
        Space(Dimensions.Margin.default())
        FilmListItemButton(
            film = film,
            onClick = rightButtonConfig.onClick,
            imageResId = rightButtonConfig.imageResId,
            descriptionResId = rightButtonConfig.descriptionResId
        )
    }
}

@Composable
fun FilmListItemContent(
    film: Film,
    onItemClick: (Film) -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    FilmListCard(
        film = film,
        onItemClick = onItemClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(Dimensions.Card.titleWeight)
        ) {
            Text(
                text = film.title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Normal
            )
        }
        Column(
            modifier = Modifier.weight(Dimensions.Card.buttonsWeight),
            horizontalAlignment = Alignment.End
        ) {
            Row(content = content, modifier = contentModifier)
        }
    }
}
