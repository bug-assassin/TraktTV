package com.pixis.trakt_api.services

import com.pixis.trakt_api.network_models.TraktShow
import retrofit2.http.GET
import rx.Observable

/**
 * Created by Dan on 11/14/2016.
 */
/**
 * <b>OAuth Required</b>
 */
interface Sync {
    @GET("sync/watchlist/shows")
    fun getWatchListShows() : Observable<List<TraktShow>>
}