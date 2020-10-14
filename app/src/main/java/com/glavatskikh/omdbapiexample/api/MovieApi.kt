package com.glavatskikh.omdbapiexample.api

import com.glavatskikh.omdbapiexample.api.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun getMovies(
        @Query("s") search: String,
        @Query("apikey") apiKey: String = "7214d7e3"
    ): Response<MoviesResponse>
}