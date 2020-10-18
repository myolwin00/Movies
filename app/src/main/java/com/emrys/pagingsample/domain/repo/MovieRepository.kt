package com.emrys.pagingsample.domain.repo

import androidx.paging.PagingData
import com.emrys.pagingsample.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(): Flow<PagingData<Movie>>

    suspend fun favorite(id: String)
}