package com.emrys.movies.domain.repo

import androidx.paging.PagingData
import com.emrys.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(): Flow<PagingData<Movie>>

    suspend fun favorite(id: String)
}