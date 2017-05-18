package com.pixis.traktTV.base

import com.pixis.trakt_api.services_fanart.ShowImages
import com.pixis.trakt_api.services_fanart.ImageService
import com.pixis.trakt_api.services_fanart.MovieImages
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

    fun getTrendingMovies(): Observable<List<Pair<Movie, MovieImages>>> {
        return movieService.getTrendingMovies().flatMap {
            return@flatMap Observable.fromIterable(it).flatMap {
                Observable.just(it.movie).zipWith(imageService.getMovieImages(it.movie.ids.tmdb).toObservable(), asPair())
            }.toList()
        }.toObservable().applySchedulers()
    }

    fun getTrendingShows(): Observable<List<Pair<Show, ShowImages>>> {
        return showService.getTrendingShows().flatMap {
            return@flatMap Observable.fromIterable(it).flatMap {
                Observable.just(it.show).zipWith(imageService.getShowImages(it.show.ids.tvdb).toObservable(), asPair())
            }.toList()
        }.toObservable().applySchedulers()
    }
}