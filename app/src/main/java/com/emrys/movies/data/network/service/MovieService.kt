package com.emrys.movies.data.network.service

import com.emrys.movies.data.network.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieResponse
}