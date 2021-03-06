package com.victormordur.gihbli.app.domain.usecase.flowable

import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepository
import com.victormordur.gihbli.app.domain.usecase.FlowableUseCase
import kotlinx.coroutines.flow.mapLatest

abstract class GetUserFilteredFilms(private val repository: FilmRepository) :
    FlowableUseCase<List<Film>> {
    override fun requestFlow() =
        repository.getUserFilmsFlow().mapLatest { it.filter { film -> evaluateCondition(film) } }

    abstract fun evaluateCondition(film: Film): Boolean
}
