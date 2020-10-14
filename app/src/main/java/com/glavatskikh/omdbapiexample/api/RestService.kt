package com.glavatskikh.omdbapiexample.api

import com.glavatskikh.omdbapiexample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RestService {

    val api: MovieApi

    init {
        api = createRetrofit().create()
    }

    private fun createRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        val httpClientBuilder = OkHttpClient.Builder().addInterceptor(logging);
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()
    }
}