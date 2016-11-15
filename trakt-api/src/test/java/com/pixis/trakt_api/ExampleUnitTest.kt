package com.pixis.trakt_api

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    /*lateinit var service: TraktService

    @Before
    fun init() {
        val networkService = TraktAPI()
        val retrofit = networkService.createRetrofit(networkService.createOkHttpClient())
        service = retrofit.create(TraktService::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun TestListMoviesRequest() {
        val date = SimpleDateFormat("yyyy-MM-dd")
                .parse("2014-09-01")
                .toRetrofitDate()
        val response = service.getAllTVShows(date, 7).execute()

        assert(response.isSuccessful)
    }
    */
}