package com.emrys.movies.di

import com.emrys.movies.data.network.service.MovieService
import com.emrys.movies.data.network.RetrofitFactory
import com.emrys.movies.data.network.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideMovieService(
        retrofit: Retrofit
    ) = retrofit.create<MovieService>()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) = RetrofitFactory.create(okHttpClient)

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = RetrofitFactory.createOkHttpClient(tokenInterceptor, httpLoggingInterceptor)
}