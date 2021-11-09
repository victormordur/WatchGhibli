package com.victormordur.gihbli.app.presentation.list.content.empty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.presentation.style.Dimensions

@Composable
fun EmptyListContent(textResId: Int) {
    Column(
        modifier = Modifier.fillMaxSize().padding(Dimensions.Margin.extra()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = textResId),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Normal,
            color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyPreview() {
    EmptyListContent(R.string.watch_list_empty)
}
