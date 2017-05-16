package com.pixis.trakt_api.services_trakt.models

import io.realm.RealmObject

open class Ids(open var trakt: String = "",
               open var slug: String = "",
               open var tvdb: String = "",
               open var imdb: String = "",
               open var tmdb: String = "",
               open var tvrage: String = "") : RealmObject()

data class Movie(val ids: Ids = Ids(), val title: String = "", val year: Int = 0)
data class Show(val ids : Ids = Ids(), val title : String = "", val year : Int = 0, val primaryKey: String = ids.trakt)