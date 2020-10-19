package com.emrys.movies.data.network.response

import com.squareup.moshi.Json

data class MovieResponse(
    val results: List<MovieData>?,
    val page: Int,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
)