package com.pixis.traktTV.repository

import com.pixis.traktTV.data.models.TrackedItem
import com.pixis.trakt_api.models.CalendarShowEntry
import com.pixis.trakt_api.utils.applySchedulers
import io.realm.RealmModel
import rx.Observable

/**
 * Created by Dan on 11/20/2016.
 */
//Inspired by https://github.com/FabianTerhorst/ApiClient/blob/master/apiclient/src/main/java/io/fabianterhorst/apiclient/ApiObserver.java
class Repository(val remoteRepo: RemoteRepository, val localRepo: LocalRepository) {
    fun getWatchList(): Observable<List<TrackedItem>> {
        return getRemoteDataAndSaveIt(remoteRepo.getWatchList(), TrackedItem::class.java)
    }

    private fun <T : RealmModel> getRemoteDataAndSaveIt(remoteOperation: Observable<List<T>>, realmClass: Class<T>): Observable<List<T>> {
        val localQuery: Observable<List<T>> = localRepo.getItems(realmClass)
                .filter { it.isLoaded }
                .switchMap { Observable.just(it) } //Not interested in old emission

        //Remote Query is just a side effect
        val remoteQuery = remoteOperation.applySchedulers().doOnNext { localRepo.saveData(it) }

        //Do the remote query only
        /*if (forceUpdate) {
            return remoteQuery
        } else { //Allow cached data*/
        return Observable.defer {
            Observable.create<List<T>>({ subscriber ->
                localQuery.subscribe(subscriber)
                remoteQuery.subscribe({}, { subscriber.onError(it) }, {})
            })
        }
        //}
    }

    fun getShowsNextDays(days: Int): Observable<List<CalendarShowEntry>> {
        return remoteRepo.getShowsNextDays(days)
    }

}