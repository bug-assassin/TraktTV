package com.pixis.trakt_api

import com.pixis.trakt_api.services.Sync
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class SyncTest : BaseTest() {

    lateinit var sync : Sync

    @Before
    override fun init() {
        super.init()
        sync = retrofit.create(Sync::class.java)
    }

    @Test
    fun TestSync() {
        val watchList = sync.getWatchListShows()
                .doOnError { error(it) }
                .toBlocking()
                .first()
        assert(watchList.isNotEmpty())
    }
}