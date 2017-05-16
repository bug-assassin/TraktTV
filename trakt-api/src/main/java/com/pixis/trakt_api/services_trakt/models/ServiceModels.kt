package com.pixis.trakt_api.services_trakt.models

data class TrendingMovie(val watchers: Int = 0, val movie: Movie)

data class TrendingShow(val watchers: Int = 0, val show: Show)