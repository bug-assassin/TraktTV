package com.pixis.traktTV.data.models

import io.realm.RealmObject

/**
 * Created by Dan on 11/21/2016.
 */
open class Episode(
        open var title: String = "",
        open var episode_number: String = "",
        open var release_date: String = ""
) : RealmObject()