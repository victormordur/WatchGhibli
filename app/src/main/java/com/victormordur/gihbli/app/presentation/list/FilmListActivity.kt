package com.victormordur.gihbli.app.presentation.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.presentation.common.ActionResult
import com.victormordur.gihbli.app.presentation.list.navigation.FilmListNavigation
import com.victormordur.gihbli.app.presentation.style.GhibliTheme
import com.victormordur.gihbli.app.presentation.view.composable.BottomNavigationBar
import com.victormordur.gihbli.app.presentation.view.composable.ErrorSnackBar
import com.victormordur.gihbli.app.presentation.view.composable.InfoSnackBar
import com.victormordur.gihbli.app.presentation.view.composable.SnackBarType
import com.victormordur.gihbli.app.presentation.view.composable.Toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListActivity : ComponentActivity() {

    private val viewModel: FilmListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GhibliTheme {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        Toolbar(title = R.string.app_name)
                    },
                    bottomBar = { BottomNavigationBar(navController) },
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        SnackbarHost(it) { data ->
                            when (data.actionLabel) {
                                SnackBarType.Info.name -> {
                                    InfoSnackBar(data = data)
                                }
                                SnackBarType.Error.name -> {
                                    ErrorSnackBar(data = data)
                                }
                            }
                        }
                    }
                ) { innerPadding ->

                    val actionError = viewModel.actionErrorFlow.collectAsState(initial = null)
                    val actionResult = viewModel.actionResultFlow.collectAsState(initial = null)

                    actionError.value?.let {
                        ShowErrorSnackBar(scaffoldState, it)
                    }

                    actionResult.value?.let {
                        ShowInfoSnackBar(scaffoldState, it)
                    }

                    Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
                        FilmListNavigation(
                            navController = navController,
                            content = viewModel,
                            actions = viewModel,
                            onItemClick = { /* TODO launch item details screen */ },
                            lifecycleScope = lifecycleScope
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowInfoSnackBar(scaffoldState: ScaffoldState, actionSuccess: ActionResult) {
    val message = stringResource(id = actionSuccess.messageId)
    LaunchedEffect(actionSuccess) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = message,
            actionLabel = SnackBarType.Info.name,
            duration = SnackbarDuration.Short
        )
    }
}

@Composable
private fun ShowErrorSnackBar(scaffoldState: ScaffoldState, throwable: Throwable) {
    val message = stringResource(id = R.string.unexpected_error)
    LaunchedEffect(throwable) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = message,
            actionLabel = SnackBarType.Error.name,
            duration = SnackbarDuration.Short
        )
    }
}
