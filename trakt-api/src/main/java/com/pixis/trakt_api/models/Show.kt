package com.pixis.trakt_api.models

/**
 * Created by Dan on 11/11/2016.
 */

data class Show(val ids : Ids = Ids(), val title : String = "", val year : Int = 0, val primaryKey: String = ids.trakt)
