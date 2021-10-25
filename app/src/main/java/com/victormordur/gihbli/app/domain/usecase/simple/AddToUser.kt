package com.victormordur.gihbli.app.domain.usecase.simple

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.ParameterizedUseCase

class AddToUser(private val repository: FilmRepositoryContract) : ParameterizedUseCase<Film, Unit> {
    override suspend fun execute(parameter: Film) {
        repository.addToUser(parameter)
    }
}
