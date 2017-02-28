package com.pixis.trakt_api.models

data class TraktShow(val show : Show, val listed_at : String, val rank: Int, val type: String = "")