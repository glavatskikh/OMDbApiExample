package com.glavatskikh.omdbapiexample.api

import com.glavatskikh.omdbapiexample.api.response.MoviesResult

object MovieRepository {
    suspend fun getMovies(search: String): MoviesResult {
        val response = RestService.api.getMovies(search)
        if (response.isSuccessful) {
            response.body()?.search?.let {
                return MoviesResult.Success(it)
            }
            return MoviesResult.Error("body is empty")
        }
        return MoviesResult.Error("${response.code()} ${response.errorBody()}")
    }
}