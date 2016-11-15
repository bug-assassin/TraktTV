package com.pixis.trakt_api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Dan on 11/11/2016.
 */
class TraktAPI(val tokenDatabase: TokenDatabase) {
    fun createOkHttpClient(client_id: String): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor { chain ->
            val original = chain.request()

            val newRequest = original.newBuilder()
                    .addHeader("Content-type", "application/json")
                    .addHeader("trakt-api-key", client_id)
                    .addHeader("trakt-api-version", "2")
                    .build()
            return@addInterceptor chain.proceed(newRequest)
        }.addInterceptor(logging)

        okHttpClient.addInterceptor { chain ->
            var newRequest = chain.request()
            if (tokenDatabase.isAuthenticated()) {
                newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + tokenDatabase.getAccessToken())
                        .build()
            }

            return@addInterceptor chain.proceed(newRequest)
        }

        return okHttpClient
    }

    fun createRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit.Builder {
        return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
    }

    fun refreshToken() : String {
        //TODO
        //tokenDatabase.getToken().refresh_token
        return ""
    }

}