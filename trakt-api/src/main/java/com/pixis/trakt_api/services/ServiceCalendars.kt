package com.pixis.trakt_api.services

import com.pixis.trakt_api.models.CalendarShowEntry
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created from https://github.com/UweTrottmann/trakt-java/blob/master/src/main/java/com/uwetrottmann/trakt5/services/ServiceCalendars.java
 */
interface ServiceCalendars {

    /**
     * **OAuth Required**

     * @see .shows
     */
    @GET("calendars/my/shows/{startdate}/{days}")
    fun getMyShows(
            @Path("startdate") startDate: String,
            @Path("days") days: Int
    ): Observable<List<CalendarShowEntry>>

    /**
     * **OAuth Required**

     * @see .newShows
     */
    @GET("calendars/my/shows/new/{startdate}/{days}")
    fun getMyNewShows(
            @Path("startdate") startDate: String,
            @Path("days") days: Int
    ): Observable<List<CalendarShowEntry>>
/*
    /**
     * **OAuth Required**

     * @see .seasonPremieres
     */
    @GET("calendars/my/shows/premieres/{startdate}/{days}")
    fun mySeasonPremieres(
            @Path("startdate") startDate: String,
            @Path("days") days: Int
    ): Observable<List<CalendarShowEntry>>

    /**
     * **OAuth Required**

     * @see .movies
     */
    @GET("calendars/my/movies/{startdate}/{days}")
    fun myMovies(
            @Path("startdate") startDate: String,
            @Path("days") days: Int
    ): Observable<List<CalendarMovieEntry>>

    /**
     * Returns all shows airing during the time period specified.

     * @param startDate Start the calendar on this date. Example: 2014-09-01.
     * *
     * @param days Number of days to display. Example: 7.
     */
    @GET("calendars/all/shows/{startdate}/{days}")
    fun shows(
            @Path("startdate") startDate: String,
            @Path("days") days: Int
    ): Observable<List<CalendarShowEntry>>

    /**
     * Returns all new show premieres (season 1, episode 1) airing during the time period specified.

     * @param startDate Start the calendar on this date. Example: 2014-09-01.
     * *
     * @param days Number of days to display. Example: 7.
     */
    @GET("calendars/all/shows/new/{startdate}/{days}")
    fun newShows(
            @Path("startdate") startDate: String,
            @Path("days") days: Int
    ): Observable<List<CalendarShowEntry>>

    /**
     * Returns all show premieres (any season, episode 1) airing during the time period specified.

     * @param startDate Start the calendar on this date. Example: 2014-09-01.
     * *
     * @param days Number of days to display. Example: 7.
     */
    @GET("calendars/all/shows/premieres/{startdate}/{days}")
    fun seasonPremieres(
            @Path("startdate") startDate: String,
            @Path("days") days: Int
    ): Observable<List<CalendarShowEntry>>

    /**
     * Returns all movies with a release date during the time period specified.

     * @param startDate Start the calendar on this date. Example: 2014-09-01.
     * *
     * @param days Number of days to display. Example: 7.
     */
    @GET("calendars/all/movies/{startdate}/{days}")
    fun movies(
            @Path("startdate") startDate: String,
            @Path("days") days: Int
    ): Observable<List<CalendarMovieEntry>>
*/
}