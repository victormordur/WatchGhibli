package com.victormordur.gihbli.app.domain.usecase.flowable

import com.victormordur.gihbli.app.data.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract

class GetUserToBeWatchedFilms(repository: FilmRepositoryContract) :
    GetUserFilteredFilms(repository) {
    override fun evaluateCondition(film: Film) = !film.watched
}
