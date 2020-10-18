package com.emrys.pagingsample.feature.movie.list

sealed class MovieAction {
    data class Favorite(val id: String): MovieAction()
}