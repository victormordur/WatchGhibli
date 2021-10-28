package com.victormordur.gihbli.app.presentation.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.presentation.style.Styles
import com.victormordur.gihbli.app.presentation.view.Toolbar

class FilmListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = Styles.ghibliColorPalette()) {
                Scaffold(topBar = {
                    Toolbar(title = R.string.app_name)
                }) {
            }
            }
        }
    }
}
