package com.pixis.trakt_api.services_fanart

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {
    @GET("movies/{id}")
    fun getMovieImages(@Path("id") tvdb_id : String) : Single<FanArtImages>

    @GET("tv/{id}")
    fun getShowImages(@Path("id") tvdb_id : String) : Single<FanArtImages>
}

data class FanArtImages(val name : String,
                        val thetvdb_id : Int,
                        val clearlogo: List<FanArtImage> = ArrayList(),
                        val hdtvlogo: List<FanArtImage> = ArrayList(),
                        val clearart: List<FanArtImage> = ArrayList(),
                        val showbackground: List<FanArtImage> = ArrayList(),
                        val tvthumb: List<FanArtImage> = ArrayList(),
                        val seasonposter: List<FanArtImage> = ArrayList(),
                        val seasonthumb: List<FanArtImage> = ArrayList(),
                        val hdclearart: List<FanArtImage> = ArrayList(),
                        val tvbanner: List<FanArtImage> = ArrayList(),
                        val characterart: List<FanArtImage> = ArrayList(),
                        val tvposter: List<FanArtImage> = ArrayList(),
                        val seasonbanner: List<FanArtImage> = ArrayList())

data class FanArtImage(val id : String, val url : String, val lang : String, val likes : Int, val season: String = "all") {
    fun getPreviewUrl() = url.replace("/fanart/", "/preview/")
}