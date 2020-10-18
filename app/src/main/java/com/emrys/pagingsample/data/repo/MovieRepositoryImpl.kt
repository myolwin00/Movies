package com.emrys.pagingsample.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.emrys.pagingsample.data.source.MoviePagingSource
import com.emrys.pagingsample.data.network.service.MovieService
import com.emrys.pagingsample.domain.model.Movie
import com.emrys.pagingsample.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
): MovieRepository {

    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ), pagingSourceFactory = { MoviePagingSource(movieService) }).flow
    }

    override suspend fun favorite(id: String) {
    }
}