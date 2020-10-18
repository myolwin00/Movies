package com.emrys.pagingsample.domain.model

data class Movie(
    val id: String,
    val title: String,
    val image: String,
    val isFavorite: Boolean = false
)