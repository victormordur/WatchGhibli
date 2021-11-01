package com.victormordur.gihbli.app.presentation.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.victormordur.gihbli.app.R

object Dimensions {

    const val IMAGE_RATIO_16_9: Float = 16f / 9f

    object Margin {
        @Composable
        fun default() = dimensionResource(R.dimen.margin)

        @Composable
        fun double() = dimensionResource(R.dimen.margin_double)

        @Composable
        fun large() = dimensionResource(R.dimen.margin_large)

        @Composable
        fun extra() = dimensionResource(R.dimen.margin_extra)
    }

    object Radius {
        @Composable
        fun card() = dimensionResource(R.dimen.card_radius)
    }

    object Card {
        const val titleWeight = 0.6f
        const val buttonsWeight = 0.4f

        @Composable
        fun totalHeight() = dimensionResource(R.dimen.card_item_height)
    }
}
