package com.pixis.traktTV.data.models

import com.pixis.trakt_api.models.Episode

/**
 * Created by Dan on 11/14/2016.
 */

data class TrackedItem(val title : String, val poster_path: String, val episode : Episode)