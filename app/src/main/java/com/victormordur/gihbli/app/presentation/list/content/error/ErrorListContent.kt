package com.victormordur.gihbli.app.presentation.list.content.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.presentation.list.content.empty.EmptyListContent

@Composable
fun ErrorListContent() {
    EmptyListContent(R.string.generic_list_error)
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    ErrorListContent()
}
