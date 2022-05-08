package com.victormordur.gihbli.app.presentation.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.presentation.style.Dimensions

@Composable
fun FilmListCard(
    film: Film,
    onItemClick: (Film) -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Card(
        shape = RoundedCornerShape(Dimensions.Radius.card()),
        modifier = Modifier
            .padding(Dimensions.Margin.default())
            .height(Dimensions.Card.totalHeight())
            .clickable { onItemClick.invoke(film) } then modifier,
    ) {
        Box {
            NetworkImage(url = film.imageURL)
            Row(
                content = content,
                modifier = Modifier
                    .background(color = MaterialTheme.colors.secondary)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(Dimensions.Margin.double())
                    .align(Alignment.BottomCenter) then contentModifier,
                verticalAlignment = Alignment.CenterVertically,
            )
        }
    }
}
