package com.victormordur.gihbli.app.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.presentation.ViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class CommonViewModel : ViewModel() {
    private val actionErrorChannel: Channel<Throwable> = Channel(CONFLATED)
    val actionErrorFlow = actionErrorChannel.receiveAsFlow()

    private val actionResultChannel: Channel<ActionResult> = Channel(CONFLATED)
    val actionResultFlow = actionResultChannel.receiveAsFlow()

    protected fun <R : ActionResult> launchHandledAction(block: suspend () -> R) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Timber.w("CommonViewModel - CoroutineExceptionHandler: $throwable")
            viewModelScope.launch {
                actionErrorChannel.send(throwable)
            }
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            val result = block.invoke()
            if (result !is NoResult) {
                actionResultChannel.send(result)
            }
        }
    }

    protected fun launchHandledActionNoResult(block: suspend () -> Unit) = launchHandledAction {
        block.invoke()
        NoResult
    }

    protected fun <T> Flow<T>.asViewStateFlow(initialValue: ViewState<T> = ViewState.Loading()) =
        handleErrors().stateIn(viewModelScope, SharingStarted.Lazily, initialValue)

    private fun <T> Flow<T>.handleErrors(): Flow<ViewState<T>> = catch { e ->
        ViewState.Error<T>(e)
    }.map {
        ViewState.Content(it)
    }
}

interface ActionResult {
    val messageId: Int
}

object NoResult : ActionResult {
    override val messageId: Int = R.string.empty
}
