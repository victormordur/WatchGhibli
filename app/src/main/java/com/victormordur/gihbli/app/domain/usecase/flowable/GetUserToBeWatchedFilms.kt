package com.victormordur.gihbli.app.domain.usecase.flowable

import com.victormordur.gihbli.app.domain.model.Film
import com.victormordur.gihbli.app.domain.repository.FilmRepository

class GetUserToBeWatchedFilms(repository: FilmRepository) :
    GetUserFilteredFilms(repository) {
    override fun evaluateCondition(film: Film) = !film.watched
}
