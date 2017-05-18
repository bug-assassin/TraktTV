package com.pixis.traktTV.base

import com.pixis.trakt_api.services_fanart.FanArtImages
import com.pixis.trakt_api.services_fanart.ImageService
import com.pixis.trakt_api.services_trakt.MovieService
import com.pixis.trakt_api.services_trakt.ShowService
import com.pixis.trakt_api.services_trakt.models.Movie
import com.pixis.trakt_api.services_trakt.models.Show
import com.pixis.trakt_api.utils.applySchedulers
import com.pixis.trakt_api.utils.asPair
import io.reactivex.Observable

class Repository(private val movieService: MovieService,
                 private val showService: ShowService,
                 private val imageService: ImageService) {

    fun getTrendingMovies(): Observable<List<Pair<Movie, FanArtImages>>> {
        return Observable.empty()
    }

    fun getTrendingShows(): Observable<List<Pair<Show, FanArtImages>>> {
        return showService.getTrendingShows().flatMap {
            return@flatMap Observable.fromIterable(it).flatMap {
                Observable.just(it.show).zipWith(imageService.getShowImages(it.show.ids.tvdb).toObservable(), asPair<Show, FanArtImages>())
            }.toList()
        }.toObservable().applySchedulers()
    }
}