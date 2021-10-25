package com.victormordur.gihbli.app.domain.usecase.flowable

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract
import com.victormordur.gihbli.app.domain.usecase.FlowableUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

class GetCatalogueFilteredByUserFilms(private val repository: FilmRepositoryContract) :
    FlowableUseCase<List<Film>> {
    override fun execute(): Flow<List<Film>> {
        return combine(
            repository.getCatalogueFilms().distinctUntilChanged(),
            repository.getUserFilms().distinctUntilChanged()
        ) { catalogue, user ->
            catalogue.mapNotNull {
                if (user.contains(it)) {
                    null
                } else {
                    it
                }
            }
        }
    }
}
