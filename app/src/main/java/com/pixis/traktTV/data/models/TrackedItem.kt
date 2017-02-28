package com.pixis.traktTV.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TrackedItem(
        @PrimaryKey
        open var traktId: String = "",
        //@Suppress("CanBeParameter") open var ids: Ids = Ids(),
        open var title: String = "",
        open var poster_path: String = "",
        open var episode: Episode = Episode()) : RealmObject() {

    /*@PrimaryKey
    open var traktId: String = ids.trakt*/

}