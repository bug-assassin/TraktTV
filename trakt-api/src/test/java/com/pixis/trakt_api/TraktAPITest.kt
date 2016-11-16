package com.pixis.trakt_api

import com.pixis.trakt_api.Token.MockTokenDatabase
import com.pixis.trakt_api.Token.TokenStorage
import com.pixis.trakt_api.services.Sync
import com.pixis.trakt_api.utils.applySchedulers
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class TraktAPITest {

    val baseURL = "https://api.trakt.tv"
    val client_id = "fdacbaf7fe41bc6d5bf179a4a2f3d261a5ff5d8ae2e9fcc9eef48b37bb043c20"
    val client_secret = "8828873a1cbb07126bf76c305ea13d0f9237092896241196f7300b799ae9d787"
    val redirect_url = "urn:ietf:wg:oauth:2.0:oob"


    lateinit var sync : Sync

    @Before
    fun init() {
        val tokenStorage : TokenStorage = MockTokenDatabase(BuildConfig.MOCK_AUTHENTICATION_TOKEN)

        val networkService = TraktAPI(tokenStorage = tokenStorage)
        val okHttpClient = networkService.createOkHttpClient(client_id = client_id).build()

        val retrofit = networkService.createRetrofit(okHttpClient = okHttpClient, baseUrl = baseURL).build()


        //Services
        sync = retrofit.create(Sync::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun TestSync() {
        val watchList = sync.getWatchListShows()
                .applySchedulers()
                .toBlocking()
                .first()

        assert(watchList.size > 0)
    }

}