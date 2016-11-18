package com.pixis.trakt_api.network_models

import com.pixis.trakt_api.models.Show

/**
 * Created by Dan on 11/14/2016.
 */
data class TraktShow(val show : Show, val listed_at : String, val rank: Int, val type: String = "")