package com.victormordur.gihbli.app.domain.usecase.simple

import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.ParameterizedUseCase

class MarkUserToBeWatched(private val repository: FilmRepositoryContract) :
    ParameterizedUseCase<String, Unit> {
    override suspend fun execute(parameter: String) {
        repository.markToBeWatched(parameter)
    }
}
