package com.emrys.movies.feature.movie.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emrys.movies.domain.model.Movie
import com.emrys.movies.domain.repo.MovieRepository
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieListViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    lateinit var movies: Flow<PagingData<Movie>>

    private val favoriteIDsChannel = ConflatedBroadcastChannel(mutableSetOf<String>())

    init {
        viewModelScope.launch {
            movies = movieRepository.getMovies()
//                .combine(favoriteIDsChannel.asFlow()) { pagingData, favoriteIDs ->
//                    pagingData.map {
//                        it.copy(isFavorite = favoriteIDs.contains(it.id))
//                    }
//                }
                .cachedIn(viewModelScope)
                .catch {
                    // todo: handle error
                }
        }
    }

    fun favorite(id: String) {
        viewModelScope.launch {
            // todo: simulate the fav movie function
            delay(1000)
            favoriteIDsChannel.offer(
                favoriteIDsChannel.value.apply {
                    add(id)
                }
            )
        }
    }
}