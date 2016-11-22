package com.pixis.traktTV.repository

import com.pixis.traktTV.data.models.Episode
import com.pixis.traktTV.data.models.TrackedItem
import com.pixis.trakt_api.image_api.FanArtImages
import com.pixis.trakt_api.image_api.FanArtMedia
import com.pixis.trakt_api.image_api.ImageLoading
import com.pixis.trakt_api.network_models.TraktShow
import com.pixis.trakt_api.services.Sync
import com.pixis.trakt_api.utils.asPair
import rx.Observable

/**
 * Created by Dan on 11/20/2016.
 */
class RemoteRepository(private val syncService: Sync, private val imageLoadingAPI: ImageLoading) {
    fun getWatchList() : Observable<List<TrackedItem>> {
        return syncService.getWatchListShows()
                .flatMap {
                    Observable.from(it)
                }
                .flatMap {
                    val show = Observable.just(it)
                    val showImage = imageLoadingAPI.getImages(FanArtMedia.SHOW, it.show.ids.tvdb)
                    return@flatMap Observable.combineLatest(show, showImage, asPair<TraktShow, FanArtImages>())
                }
                .map { it -> TrackedItem(traktId = it.first.show.ids.trakt, title = it.first.show.title, poster_path = it.second.tvposter[0].preview_url, episode = Episode(title = "Test", episode_number = "S02E3", release_date = "Tomorrow")) }
                .toList()
    }
}