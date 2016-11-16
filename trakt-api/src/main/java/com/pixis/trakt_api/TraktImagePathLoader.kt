package com.pixis.trakt_api

import com.pixis.trakt_api.models.Ids

/**
 * Created by Dan on 11/16/2016.
 */
//TODO
@Deprecated("Update or delete this")
class TraktImagePathLoader() {
    /**
     * Gets the prefered image path from the ids
     */
    fun getImagePath(ids : Ids, media : Media, imageType : ImageType) {
        //TODO actually add logic here
        //For now return fanart.tv
        //TODO extract api_key
        val id = ids.tvdb
        val api_key = "c9a9bbee5c1550e6556dbf36d6d25dbd"

        val path = "http://webservice.fanart.tv/v3/" + media.toString()  + "/" + id + "?api_key=" + api_key
    }
}

enum class Media(val traktMedia : String) {
    SHOWS("tv");//TODO add the rest

    override fun toString(): String {
        return traktMedia
    }
}

enum class ImageType(val traktMedia : String) {
    POSTER("poster");//TODO add the rest

    override fun toString(): String {
        return traktMedia
    }
}