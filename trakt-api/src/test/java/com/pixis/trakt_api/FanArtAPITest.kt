package com.pixis.trakt_api

import com.pixis.trakt_api.services_fanart.FanArtImage
import com.pixis.trakt_api.services_fanart.ImageService
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test

class FanArtAPITest {

    val fanart_baseURL = BuildConfig.fanart_baseURL
    val fanart_ApiKey = BuildConfig.fanart_ApiKey


    lateinit var imageService: ImageService

    @Before
    fun init() {
        val loggingIntercepter = HttpLoggingInterceptor()
        loggingIntercepter.level = HttpLoggingInterceptor.Level.NONE

        val retrofit = RestAPI.createRetrofit(fanart_baseURL,
                FanArtAPI.createOkHttpClient(fanart_ApiKey)
                .addInterceptor(loggingIntercepter)
                .build()
        )

        imageService = retrofit.create(ImageService::class.java)
    }

    @Test
    fun TestFanArtPreviewImage() {
        val url = "test/fanart/"
        val preview_url = "test/preview/"

        val fanArtImage = FanArtImage(id = "a", url = url, lang = "en", likes = 1, season = "all")

        assert(fanArtImage.preview_url == preview_url)
    }

    @Test
    fun TestShowImages() {
        val showId = 296762
        val showName = "Westworld"

        val loadedImages = imageService.getShowImages(showId.toString()).blockingGet()

        assert(loadedImages.thetvdb_id == showId)
        assert(loadedImages.name == showName)

        assert(loadedImages.hdtvlogo.isNotEmpty())
        assert(loadedImages.showbackground.isNotEmpty())
        assert(loadedImages.tvbanner.isNotEmpty())
        assert(loadedImages.tvposter.isNotEmpty())
        assert(loadedImages.tvthumb.isNotEmpty())
    }

}