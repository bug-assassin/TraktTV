package com.pixis.trakt_api.services_trakt

import android.graphics.Movie
import io.reactivex.Single
import retrofit2.http.GET

interface MovieService {
    @GET("/movies/trending")
    fun getTrendingMovies(): Single<List<Movie>>
}
