package com.pixis.trakt_api

import com.pixis.trakt_api.Token.MockTokenDatabase
import com.pixis.trakt_api.Token.TokenStorage
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

open class BaseTest {

    val baseURL = "https://api.trakt.tv"
    val client_id = "fdacbaf7fe41bc6d5bf179a4a2f3d261a5ff5d8ae2e9fcc9eef48b37bb043c20"

    open val loggingLevel = HttpLoggingInterceptor.Level.BODY

    lateinit var retrofit : Retrofit

    open fun init() {
        val tokenStorage : TokenStorage = MockTokenDatabase(BuildConfig.MOCK_AUTHENTICATION_TOKEN)

        val networkService = TraktAPI(tokenStorage = tokenStorage)
        val okHttpClient = networkService.createOkHttpClient(client_id, loggingLevel).build()
        retrofit = networkService.createRetrofit(okHttpClient, baseURL).build()
    }

}