package com.pixis.trakt_api.models

import com.pixis.traktTV.data.models.Episode


data class CalendarShowEntry(
        var first_aired: String = "",
        var episode: Episode = Episode(),
        var show: Show = Show()
)