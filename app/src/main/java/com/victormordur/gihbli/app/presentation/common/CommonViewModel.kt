package com.victormordur.gihbli.app.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victormordur.gihbli.app.domain.usecase.ParameterizedUseCase
import com.victormordur.gihbli.app.presentation.ViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class CommonViewModel : ViewModel() {
    private val actionErrorChannel: Channel<Throwable> = Channel(CONFLATED)
    val actionErrorFlow = actionErrorChannel.receiveAsFlow()

    protected fun <T : Any> ParameterizedUseCase<T, Unit>.runWith(parameter: T): Job {
        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            CoroutineScope(coroutineContext).launch {
                actionErrorChannel.send(throwable)
            }
        }

        return viewModelScope.launch(coroutineExceptionHandler) {
            execute(parameter)
        }
    }

    protected fun <T> Flow<T>.asViewStateFlow(initialValue: ViewState<T> = ViewState.Loading()) =
        handleErrors().stateIn(viewModelScope, SharingStarted.Lazily, initialValue)

    private fun <T> Flow<T>.handleErrors(): Flow<ViewState<T>> = catch { e ->
        ViewState.Error<T>(e)
    }.map {
        ViewState.Content(it)
    }
}
