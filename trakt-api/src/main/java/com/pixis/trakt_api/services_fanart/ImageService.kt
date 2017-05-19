package com.pixis.trakt_api.services_fanart

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {
    @GET("movies/{id}")
    fun getMovieImages(@Path("id") tvdb_id : String) : Single<MovieImages>

    @GET("tv/{id}")
    fun getShowImages(@Path("id") tvdb_id : String) : Single<ShowImages>
}

data class MovieImages(val name : String,
                      val thetvdb_id : Int,
                      val hdmovielogo: List<FanArtImage> = emptyList(),
                      val moviedisc: List<FanArtImage> = emptyList(),
                      val movielogo: List<FanArtImage> = emptyList(),
                      val movieposter: List<FanArtImage> = emptyList(),
                      val hdmovieclearart: List<FanArtImage> = emptyList(),
                      val movieart: List<FanArtImage> = emptyList(),
                      val moviebackground: List<FanArtImage> = emptyList(),
                      val moviebanner: List<FanArtImage> = emptyList(),
                      val moviethumb: List<FanArtImage> = emptyList())

data class ShowImages(val name : String,
                      val thetvdb_id : Int,
                      val clearlogo: List<FanArtImage> = emptyList(),
                      val hdtvlogo: List<FanArtImage> = emptyList(),
                      val clearart: List<FanArtImage> = emptyList(),
                      val showbackground: List<FanArtImage> = emptyList(),
                      val tvthumb: List<FanArtImage> = emptyList(),
                      val seasonposter: List<FanArtImage> = emptyList(),
                      val seasonthumb: List<FanArtImage> = emptyList(),
                      val hdclearart: List<FanArtImage> = emptyList(),
                      val tvbanner: List<FanArtImage> = emptyList(),
                      val characterart: List<FanArtImage> = emptyList(),
                      val tvposter: List<FanArtImage> = emptyList(),
                      val seasonbanner: List<FanArtImage> = emptyList())

data class FanArtImage(val id : String, val url : String, val lang : String, val likes : Int, val season: String = "all") {
    fun getPreviewUrl() = url.replace("/fanart/", "/preview/")
}