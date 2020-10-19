package com.emrys.movies.di

import com.emrys.movies.domain.repo.MovieRepository
import com.emrys.movies.data.repo.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository
}