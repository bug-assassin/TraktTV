package com.pixis.trakt_api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class FanArtAPI(val fanArtApiKey : String) {

    val baseUrl : String = "http://webservice.fanart.tv/v3/"

    fun createOkHttpClient(): OkHttpClient.Builder {
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

    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
    }

}