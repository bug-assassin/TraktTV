package com.pixis.trakt_api

import com.pixis.trakt_api.image_api.FanArtMedia
import com.pixis.trakt_api.image_api.ImageLoading
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test

/**
 * Created by Dan on 11/16/2016.
 */
class FanArtImagesTest {
    val api_key = "c9a9bbee5c1550e6556dbf36d6d25dbd"

    lateinit var imageLoadingAPI: ImageLoading

    @Before
    fun init() {
        val fanArtApi = FanArtAPI(api_key)
        val okHttp = fanArtApi.createOkHttpClient(HttpLoggingInterceptor.Level.NONE).build()
        val imageRetrofit = fanArtApi.createRetrofit(okHttp).build()

        imageLoadingAPI = imageRetrofit.create(ImageLoading::class.java)
    }

    @Test
    fun TestImageLoading() {
        val showId = 296762
        val showName = "Westworld"

        val loadedImages = imageLoadingAPI
                                .getImages(FanArtMedia.SHOW, showId.toString())
                                .toBlocking()
                                .first()

        assert(loadedImages.thetvdb_id == showId)
        assert(loadedImages.name == showName)

        assert(loadedImages.hdclearart.size > 0)
        assert(loadedImages.hdtvlogo.size > 0)
        assert(loadedImages.showbackground.size > 0)
        assert(loadedImages.tvbanner.size > 0)
        assert(loadedImages.tvposter.size > 0)
        assert(loadedImages.tvthumb.size > 0)
    }

}