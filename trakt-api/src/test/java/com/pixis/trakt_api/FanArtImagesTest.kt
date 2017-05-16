package com.pixis.trakt_api

import com.pixis.trakt_api.image_api.FanArtImage
import com.pixis.trakt_api.image_api.FanArtMedia
import com.pixis.trakt_api.image_api.ImageLoading
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test

class FanArtImagesTest {
    val api_key = "c9a9bbee5c1550e6556dbf36d6d25dbd"

    lateinit var imageLoadingAPI: ImageLoading

    @Before
    fun init() {
        val loggingIntercepter = HttpLoggingInterceptor()
        loggingIntercepter.level = HttpLoggingInterceptor.Level.BODY

        val fanArtApi = FanArtAPI(api_key)

        val okHttp = fanArtApi.createOkHttpClient().addInterceptor(loggingIntercepter).build()
        val imageRetrofit = fanArtApi.createRetrofit(okHttp).build()

        imageLoadingAPI = imageRetrofit.create(ImageLoading::class.java)
    }

    @Test
    fun TestFanArtPreviewImage() {
        val url = "test/fanart/"
        val preview_url = "test/preview/"

        val fanArtImage = FanArtImage(id = "a", url = url, lang = "en", likes = 1, season = "all")

        assert(fanArtImage.preview_url == preview_url)
    }

    @Test
    fun TestImageLoading() {
        val showId = 296762
        val showName = "Westworld"

        val loadedImages = imageLoadingAPI
                                .getImages(FanArtMedia.SHOW, showId.toString()).blockingGet()

        assert(loadedImages.thetvdb_id == showId)
        assert(loadedImages.name == showName)

        assert(loadedImages.hdclearart.isNotEmpty())
        assert(loadedImages.hdtvlogo.isNotEmpty())
        assert(loadedImages.showbackground.isNotEmpty())
        assert(loadedImages.tvbanner.isNotEmpty())
        assert(loadedImages.tvposter.isNotEmpty())
        assert(loadedImages.tvthumb.isNotEmpty())
    }

}