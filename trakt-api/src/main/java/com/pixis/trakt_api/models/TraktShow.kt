package com.pixis.trakt_api.models

/**
 * Created by Dan on 11/14/2016.
 */
data class TraktShow(val show : Show, val listed_at : String, val rank: Int, val type: String = "")