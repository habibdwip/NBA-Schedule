package com.habib.nba.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    private fun createRetro(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return createRetro().create(service)
    }

    private fun getHttpClient(): OkHttpClient {
        val defaulTimeOut = 30L
        return OkHttpClient.Builder()
            .connectTimeout(defaulTimeOut, TimeUnit.SECONDS)
            .writeTimeout(defaulTimeOut, TimeUnit.SECONDS)
            .readTimeout(defaulTimeOut, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}