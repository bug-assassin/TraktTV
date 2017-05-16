package com.pixis.traktTV.repository

import com.pixis.traktTV.data.models.TrackedItem
import com.pixis.trakt_api.services_fanart.FanArtMedia
import com.pixis.trakt_api.services_fanart.ImageService
import com.pixis.trakt_api.services_trakt.Sync
import com.pixis.trakt_api.utils.asPair
import com.pixis.trakt_api.utils.toRetrofitDate
import io.reactivex.Observable
import io.reactivex.Single
import java.util.Date

class RemoteRepository(private val syncService: Sync, private val serviceCalendars: ServiceCalendars, private val imageServiceAPI: ImageService) {
    fun getWatchList() : Single<List<TrackedItem>> {
        return syncService.getWatchListShows()
                .flatMap { Observable.fromIterable(it) }
                .flatMap {
                    val show = Single.just(it)
                    val showImage = imageServiceAPI.getImages(FanArtMedia.SHOW, it.show.ids.tvdb).ambWith {  }
                    return@flatMap show.zipWith(showImage, asPair()).toObservable()
                }
                .map { it -> TrackedItem(traktId = it.first.show.ids.trakt, title = it.first.show.title, poster_path = it.second.tvposter[0].preview_url) }
                .toList()
    }

    fun getShowsNextDays(days: Int): Observable<List<CalendarShowEntry>> {
        return serviceCalendars.getMyShows(Date().toRetrofitDate().toString(), days)
    }
}