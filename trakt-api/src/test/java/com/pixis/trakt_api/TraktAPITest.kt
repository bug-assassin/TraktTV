package com.pixis.trakt_api

import com.pixis.trakt_api.utils.Token.MockTokenDatabase
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before


class TraktAPITest {
    val baseURL = "https://api.trakt.tv"
    val client_id = "fdacbaf7fe41bc6d5bf179a4a2f3d261a5ff5d8ae2e9fcc9eef48b37bb043c20"

    @Before
    fun init() {
        val loggingIntercepter = HttpLoggingInterceptor()
        loggingIntercepter.level = HttpLoggingInterceptor.Level.NONE

        val tokenStorage = MockTokenDatabase(BuildConfig.MOCK_AUTHENTICATION_TOKEN)

        val retrofit = RestAPI.createRetrofit(baseURL,
                TraktAPI.createOkHttpClient(client_id, tokenStorage)
                        .addInterceptor(loggingIntercepter)
                        .build()
        )


    }
}