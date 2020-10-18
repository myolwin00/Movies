package com.emrys.pagingsample.data.source

import androidx.paging.PagingSource
import com.emrys.pagingsample.BuildConfig
import com.emrys.pagingsample.data.network.service.MovieService
import com.emrys.pagingsample.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE = 1

class MoviePagingSource(
    private val movieService: MovieService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val response = movieService.getMovies(page)
            //todo: separate mapping logic
            val movieList = response.results?.map {
                Movie(
                    id = it.id.orEmpty(),
                    title = it.title.orEmpty(),
                    image = "${BuildConfig.BASE_IMAGE_URL}${it.posterPath.orEmpty()}"
                )
            }.orEmpty()
            LoadResult.Page(
                data = movieList,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (movieList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}