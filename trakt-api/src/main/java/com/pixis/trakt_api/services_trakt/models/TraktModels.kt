package com.pixis.trakt_api.services_trakt.models

open class Ids(val trakt: String = "",
               val slug: String = "",
               val tvdb: String = "",
               val imdb: String = "",
               val tmdb: String = "",
               val tvrage: String? = "") //: RealmModel

data class Movie(val ids: Ids, val title: String = "", val year: Int = 0)
data class Show(val ids : Ids, val title : String = "", val year : Int = 0, val primaryKey: String = ids.trakt)