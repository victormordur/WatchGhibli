package com.victormordur.gihbli.app.domain.usecase

import kotlinx.coroutines.flow.Flow

interface UseCase<ReturnType> : ParameterizedUseCase<Unit, ReturnType> {

    override suspend fun execute(parameter: Unit): ReturnType = execute()

    suspend fun execute(): ReturnType
}

interface ParameterizedUseCase<Parameter : Any, ReturnType> {
    suspend fun execute(parameter: Parameter): ReturnType
}

interface FlowableUseCase<ReturnType> {
    fun execute(): Flow<ReturnType>
}
