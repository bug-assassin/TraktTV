package com.pixis.traktTV.data.models

import com.pixis.trakt_api.models.Ids
import io.realm.RealmObject

open class Episode(
        open var season: Int = 0, //Season number
        open var number: Int = 0,//Episode Number
        open var title: String = "",
        open var release_date: String = "",
        open var ids: Ids = Ids()
) : RealmObject()