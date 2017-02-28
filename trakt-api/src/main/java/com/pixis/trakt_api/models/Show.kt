package com.pixis.trakt_api.models

data class Show(val ids : Ids = Ids(), val title : String = "", val year : Int = 0, val primaryKey: String = ids.trakt)
