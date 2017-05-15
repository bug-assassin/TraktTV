package com.pixis.trakt_api

import com.pixis.trakt_api.Token.TokenStorage
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class TraktAPI(val tokenStorage: TokenStorage) {

    fun createOkHttpClient(client_id: String, loggingLevel : HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = loggingLevel

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
        //tokenStorage.getToken().refresh_token
        return ""
    }

}