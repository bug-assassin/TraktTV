package com.pixis.trakt_api

import com.pixis.trakt_api.utils.Token.TokenStorage
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object TraktAPI {

    fun createOkHttpClient(client_id: String, tokenStorage: TokenStorage): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()

        //Authorization
        okHttpClient.addInterceptor { chain ->
            val original = chain.request()

            val newRequest = original.newBuilder()
                    .addHeader("Content-type", "application/json")
                    .addHeader("trakt-api-key", client_id)
                    .addHeader("trakt-api-version", "2")
                    .build()
            return@addInterceptor chain.proceed(newRequest)
        }

        //Authentication
        okHttpClient.addInterceptor { chain ->
            var newRequest = chain.request()
            if (tokenStorage.isAuthenticated()) {
                newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + tokenStorage.getAccessToken())
                        .build()
            }

            return@addInterceptor chain.proceed(newRequest)
        }

        //Error Handler
        /*okHttpClient.addInterceptor { chain ->
            if(!NetworkConnection.isConnected(context)) {
                throw NoInternetAvailableException()
            }

            chain.proceed(chain.request())
        }*/

        //Authenticator refreshes the token
        /*okHttpClient.authenticator { route, response ->

            val accessToken: String = refreshToken()
            //TODO stop after 3 retries

                return@authenticator response.request()
                        .newBuilder()
                        .header("Authorization", "Bearer " + accessToken)
                        .build()
        }*/

        return okHttpClient
    }

}