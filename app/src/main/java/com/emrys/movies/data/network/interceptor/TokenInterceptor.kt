package com.emrys.movies.data.network.interceptor

import com.emrys.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val urlBuilder = originalRequest.url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
        val newRequest = originalRequest.newBuilder()
            .url(urlBuilder.build())
            .build()
        return chain.proceed(newRequest)
    }
}