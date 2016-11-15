package com.pixis.trakt_api.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dan on 11/11/2016.
 */
class PathDate(val date: Date) {
    companion object {
        private val DF = object : ThreadLocal<DateFormat>() {
            override fun initialValue(): DateFormat {
                return SimpleDateFormat("yyyy-MM-dd")
            }
        }
    }

    override fun toString(): String {
        return DF.get().format(date)
    }
}
fun Date.toRetrofitDate() : PathDate {
    return PathDate(this)
}