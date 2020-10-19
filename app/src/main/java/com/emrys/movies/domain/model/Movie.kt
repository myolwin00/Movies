package com.emrys.movies.domain.model

data class Movie(
    val id: String,
    val title: String,
    val image: String,
    val isFavorite: Boolean = false
)