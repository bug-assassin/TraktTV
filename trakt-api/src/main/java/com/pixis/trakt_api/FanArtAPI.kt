package com.pixis.trakt_api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object FanArtAPI {

    fun createOkHttpClient(fanArtApiKey : String): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor { chain ->
            val original = chain.request()

            val newRequest = original.newBuilder()
                    .addHeader("Content-type", "application/json")
                    .addHeader("api-key", fanArtApiKey)
                    .build()
            return@addInterceptor chain.proceed(newRequest)
        }

        return okHttpClient
    }
}