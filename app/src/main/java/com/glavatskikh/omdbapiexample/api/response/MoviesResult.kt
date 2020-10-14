package com.glavatskikh.omdbapiexample.api.response

sealed class MoviesResult {

    data class Success(val movies: List<Movie>) : MoviesResult()

    data class Error(val message: String) : MoviesResult()
}