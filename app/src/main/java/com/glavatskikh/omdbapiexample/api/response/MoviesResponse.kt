package com.glavatskikh.omdbapiexample.api.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("Search")
    val search: List<Movie>,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("Response")
    val response: Boolean
)