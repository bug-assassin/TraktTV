package com.pixis.trakt_api.image_api

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by Dan on 11/16/2016.
 */
interface ImageLoading {
    //TODO
    @GET("{media}/{id}")
    fun getImages(@Path("media") media : FanArtMedia, @Path("id") tvdb_id : String) : Observable<FanArtImages>
}

enum class FanArtMedia(val stringValue : String) {
    SHOW("tv");

    override fun toString(): String {
        return stringValue
    }
}

data class FanArtImages(val name : String,
                        val thetvdb_id : Int,
                        val hdtvlogo : List<FanArtImage>,
                        val tvposter : List<FanArtImage>,
                        val tvthumb : List<FanArtImage>,
                        val hdclearart : List<FanArtImage>,
                        val showbackground : List<FanArtImage>,
                        val tvbanner : List<FanArtImage>) {

    //TODO add all the get image methods
    fun getPosterPath() : String {
        return tvposter[0].url
    }
}

data class FanArtImage(val id : String, val url : String, val lang : String, val likes : Int)
