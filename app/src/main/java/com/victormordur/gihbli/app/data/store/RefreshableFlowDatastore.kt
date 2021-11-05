package com.victormordur.gihbli.app.data.store

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext

abstract class RefreshableFlowDatastore<T>(private val dispatcher: CoroutineDispatcher) {
    protected val onSubscriptionFlow = MutableSharedFlow<T>().onSubscription {
        emit(onSubscription())
    }
    protected val refreshFlow = MutableSharedFlow<T>()

    abstract suspend fun onSubscription(): T
    abstract suspend fun onRefresh(): T

    fun getRefreshableFlow(): Flow<T> = merge(refreshFlow, onSubscriptionFlow).shareIn(
        CoroutineScope(dispatcher),
        SharingStarted.Eagerly
    )

    suspend fun refresh() {
        withContext(dispatcher) {
            refreshFlow.emit(onRefresh())
        }
    }
}
