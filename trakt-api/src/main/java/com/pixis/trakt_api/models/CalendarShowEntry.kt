package com.pixis.trakt_api.models

import com.pixis.traktTV.data.models.Episode


/**
 * Created by Dan on 11/26/2016.
 */
data class CalendarShowEntry(
        var first_aired: String = "",
        var episode: Episode = Episode(),
        var show: Show = Show()
)