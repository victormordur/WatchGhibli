package com.victormordur.gihbli.app.domain.usecase.flowable

import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepositoryContract

class GetUserWatchedFilms(repository: FilmRepositoryContract) : GetUserFilteredFilms(repository) {
    override fun evaluateCondition(film: Film) = film.watched
}
