package com.pixis.trakt_api.services

import com.pixis.trakt_api.network_models.TraktShow
import retrofit2.http.GET
import retrofit2.http.Header
import rx.Observable

/**
 * Created by Dan on 11/14/2016.
 */
//TODO use http://www.jsonschema2pojo.org/
/**
 * <b>OAuth Required</b>
 */
interface Sync {
    @GET("sync/watchlist/shows")
    fun getWatchListShows(@Header("X-SORT-BY") sortBy: SortBy = SortBy.RANK, @Header("X-SORT-HOW") sort: Sort = Sort.ASC): Observable<List<TraktShow>>
}

enum class SortBy(val traktName: String) {
    RANK("rank"),
    ADDED("added"),
    TITLE("title"),
    RELEASED("released"),
    RUNTIME("runtime"),
    POPULARITY("popularity"),
    PERCENTAGE("percentage"),
    votes("votes");

    override fun toString(): String {
        return traktName
    }
}

enum class Sort(val traktName: String) {
    ASC("asc"),
    DESC("desc");

    override fun toString(): String {
        return traktName
    }
}
