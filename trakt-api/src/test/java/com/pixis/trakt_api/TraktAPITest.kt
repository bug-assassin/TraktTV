package com.pixis.trakt_api

import com.pixis.trakt_api.services_trakt.MovieService
import com.pixis.trakt_api.services_trakt.ShowService
import com.pixis.trakt_api.utils.Token.MockTokenDatabase
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test


class TraktAPITest {
    val trakt_baseURL = BuildConfig.trakt_baseURL
    val trakt_client_id = BuildConfig.trakt_client_id


    lateinit var movieService: MovieService
    lateinit var showService: ShowService

    @Before
    fun init() {
        val loggingIntercepter = HttpLoggingInterceptor()
        loggingIntercepter.level = HttpLoggingInterceptor.Level.NONE

        val tokenStorage = MockTokenDatabase(BuildConfig.MOCK_AUTHENTICATION_TOKEN)

        val retrofit = RestAPI.createRetrofit(trakt_baseURL,
                TraktAPI.createOkHttpClient(trakt_client_id, tokenStorage)
                        .addInterceptor(loggingIntercepter)
                        .build()
        )

        movieService = retrofit.create(MovieService::class.java)
        showService = retrofit.create(ShowService::class.java)
    }

    @Test
    fun TestGetTrendingMovies() {
        assert(movieService.getTrendingMovies().blockingGet().isNotEmpty())
    }

    @Test
    fun TestGetTrendingShows() {
        assert(showService.getTrendingShows().blockingGet().isNotEmpty())
    }

}