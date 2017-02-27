package com.pixis.trakt_api

import com.pixis.trakt_api.services.ServiceCalendars
import com.pixis.trakt_api.utils.toRetrofitDate
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class CalendarTest : BaseTest() {

    lateinit var serviceCalendars: ServiceCalendars

    @Before
    override fun init() {
        super.init()
        serviceCalendars = retrofit.create(ServiceCalendars::class.java)
    }

    @Test
    fun TestSync() {
        val startTime = 1480946120298L //5/12/2016 8:55 am
        val watchList = serviceCalendars.getMyShows(Date(startTime).toRetrofitDate().toString(), 3)
                .toBlocking()
                .first()

        assert(watchList.size == 2)
    }
}