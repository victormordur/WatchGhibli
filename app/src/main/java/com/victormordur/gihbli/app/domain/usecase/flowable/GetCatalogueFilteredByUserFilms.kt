package com.victormordur.gihbli.app.domain.usecase.flowable

import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepository
import com.victormordur.gihbli.app.domain.usecase.FlowableUseCase
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

class GetCatalogueFilteredByUserFilms(private val repository: FilmRepository) :
    FlowableUseCase<List<Film>> {
    override fun requestFlow() =
        combine(
            repository.getCatalogueFilmsFlow().distinctUntilChanged(),
            repository.getUserFilmsFlow().distinctUntilChanged()
        ) { catalogue, user ->
            catalogue.mapNotNull { catalogueFilm ->
                if (user.any { it.id == catalogueFilm.id }) {
                    null
                } else {
                    catalogueFilm
                }
            }
        }
}
