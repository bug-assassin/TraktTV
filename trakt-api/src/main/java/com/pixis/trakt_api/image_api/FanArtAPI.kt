package com.pixis.trakt_api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Dan on 11/11/2016.
 */
class FanArtAPI(val fanArtApiKey : String) {

    val baseUrl : String = "http://webservice.fanart.tv/v3/"

    fun createOkHttpClient(loggingLevel : HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = loggingLevel

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor { chain ->
            val original = chain.request()

            val newRequest = original.newBuilder()
                    .addHeader("Content-type", "application/json")
                    .addHeader("api-key", fanArtApiKey)
                    .build()
            return@addInterceptor chain.proceed(newRequest)
        }.addInterceptor(logging)

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