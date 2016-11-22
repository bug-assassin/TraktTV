package com.pixis.trakt_api.models

/**
 * Created by Dan on 11/11/2016.
 */

data class Show(val ids : Ids, val title : String, val year : Int, val primaryKey: String = ids.trakt)
