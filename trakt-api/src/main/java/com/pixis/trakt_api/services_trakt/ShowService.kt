package com.pixis.trakt_api.services_trakt

import com.pixis.trakt_api.services_trakt.models.TrendingShow
import io.reactivex.Single
import retrofit2.http.GET

interface ShowService {
    @GET("/shows/trending")
    fun getTrendingShows(): Single<List<TrendingShow>>
}