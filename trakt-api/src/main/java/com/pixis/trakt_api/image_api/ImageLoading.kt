package com.pixis.trakt_api.image_api

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import java.util.*

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
    val preview_url: String = url.replace("/fanart/", "/preview/")

}