package com.pixis.traktTV

import com.pixis.traktTV.repository.RemoteRepository
import com.pixis.trakt_api.BuildConfig
import com.pixis.trakt_api.FanArtAPI
import com.pixis.trakt_api.Token.MockTokenDatabase
import com.pixis.trakt_api.Token.TokenStorage
import com.pixis.trakt_api.TraktAPI
import com.pixis.trakt_api.image_api.ImageLoading
import com.pixis.trakt_api.services.ServiceCalendars
import com.pixis.trakt_api.services.Sync
import junit.framework.TestCase
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class RemoteRepositoryTest {

    lateinit var remoteRepo: RemoteRepository

    val api_key = "c9a9bbee5c1550e6556dbf36d6d25dbd"

    lateinit var imageLoadingAPI: ImageLoading

    fun initImageLoading() {
        val fanArtApi = FanArtAPI(api_key)
        val okHttp = fanArtApi.createOkHttpClient(HttpLoggingInterceptor.Level.NONE).build()
        val imageRetrofit = fanArtApi.createRetrofit(okHttp).build()

        imageLoadingAPI = imageRetrofit.create(ImageLoading::class.java)
    }

    val baseURL = "https://api.trakt.tv"
    val client_id = "fdacbaf7fe41bc6d5bf179a4a2f3d261a5ff5d8ae2e9fcc9eef48b37bb043c20"

    val loggingLevel = HttpLoggingInterceptor.Level.BODY

    lateinit var retrofit : Retrofit

    fun initSync() {
        val tokenStorage : TokenStorage = MockTokenDatabase(BuildConfig.MOCK_AUTHENTICATION_TOKEN)

        val networkService = TraktAPI(tokenStorage = tokenStorage)
        val okHttpClient = networkService.createOkHttpClient(client_id, loggingLevel).build()
        retrofit = networkService.createRetrofit(okHttpClient, baseURL).build()
    }

    @Before
    fun init() {
        initSync()
        initImageLoading()

        remoteRepo = RemoteRepository(retrofit.create(Sync::class.java), retrofit.create(ServiceCalendars::class.java), imageLoadingAPI)
    }

    @Test
    @Throws(Exception::class)
    fun VerifyWatchListExists() {
        remoteRepo.getWatchList().subscribe({
            TestCase.assertTrue(it.size > 0)
        }
        ,
                {
                    error ->
                    error.printStackTrace()
                })
    }
}