package com.emrys.movies.feature.movie.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.emrys.movies.*
import com.emrys.movies.databinding.FragmentMovieListBinding
import com.emrys.movies.utils.GridSpacingItemDecoration
import com.emrys.movies.utils.dpToPx
import com.emrys.movies.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val binding by viewBinding(FragmentMovieListBinding::bind)
    private val viewModel: MovieListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter(
            onAction = {
                handleActions(it)
            }
        )

        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMovies.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = 3,
                spacing = requireContext().dpToPx(12f),
                includeEdge = true
            )
        )
        binding.rvMovies.adapter = movieAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { movieAdapter.retry() },
            footer = ReposLoadStateAdapter { movieAdapter.retry() }
        )

        lifecycleScope.launchWhenStarted {
            viewModel.movies.collectLatest {
                movieAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun handleActions(action: MovieAction) {
        when(action) {
            is MovieAction.Favorite -> {
                viewModel.favorite(action.id)
            }
        }
    }
}