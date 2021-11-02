package com.victormordur.gihbli.app.presentation.list.content.item

import com.victormordur.gihbli.app.data.model.Film

data class FilmListItemButtonConfig(
    val imageResId: Int,
    val descriptionResId: Int,
    val onClick: (Film) -> Unit
)
