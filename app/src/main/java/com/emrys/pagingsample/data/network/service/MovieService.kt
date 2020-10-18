package com.emrys.pagingsample.data.network.service

import com.emrys.pagingsample.data.network.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieResponse
}