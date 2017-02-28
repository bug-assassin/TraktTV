package com.pixis.trakt_api.models

import io.realm.RealmObject

open class Ids(open var trakt: String = "",
               open var slug: String = "",
               open var tvdb: String = "",
               open var imdb: String = "",
               open var tmdb: String = "",
               open var tvrage: String = "") : RealmObject()