package com.emrys.movies.data.network.response

import com.squareup.moshi.Json

class MovieData(
    val id: String?,
    val title: String?,
    @field:Json(name = "poster_path")
    val posterPath: String?
)