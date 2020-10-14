package com.glavatskikh.omdbapiexample.usecase

import com.glavatskikh.omdbapiexample.api.MovieRepository
import com.glavatskikh.omdbapiexample.api.response.MoviesResult

interface GetMoviesUseCase {
    suspend fun getMovies(search: String): MoviesResult
}

class GetMoviesUseCaseImpl : GetMoviesUseCase {

    override suspend fun getMovies(search: String): MoviesResult {
        return MovieRepository.getMovies(search)
    }
}