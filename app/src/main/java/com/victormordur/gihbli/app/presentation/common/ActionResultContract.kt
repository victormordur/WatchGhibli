package com.victormordur.gihbli.app.presentation.common

import kotlinx.coroutines.flow.Flow

interface ActionResultContract {
    val actionErrorFlow: Flow<Throwable>
    val actionResultFlow: Flow<ActionResult>
}
