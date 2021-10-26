package com.victormordur.gihbli.app.presentation

sealed class ViewState<T> {
    class Loading<T> : ViewState<T>()
    data class Error<T>(val error: Throwable?) : ViewState<T>()
    data class Content<T>(val data: T) : ViewState<T>()
}
