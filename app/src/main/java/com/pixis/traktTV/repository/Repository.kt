package com.pixis.traktTV.repository

import com.pixis.traktTV.data.models.TrackedItem
import com.pixis.trakt_api.models.CalendarShowEntry
import io.reactivex.Observable
import io.realm.RealmModel
import com.pixis.traktTV.views.Utils.showError
import com.pixis.trakt_api.utils.applySchedulers
import io.reactivex.Single
import io.reactivex.internal.functions.Functions

//Inspired by https://github.com/FabianTerhorst/ApiClient/blob/master/apiclient/src/main/java/io/fabianterhorst/apiclient/ApiObserver.java
class Repository(val remoteRepo: RemoteRepository, val localRepo: LocalRepository) {
    fun getWatchList(): Observable<List<TrackedItem>> {
        return getRemoteDataAndSaveIt(remoteRepo.getWatchList(), TrackedItem::class.java)
    }

    private fun <T : RealmModel> getRemoteDataAndSaveIt(remoteOperation: Single<List<T>>, realmClass: Class<T>): Observable<List<T>> {
        val localQuery: Observable<List<T>> = localRepo.getItems(realmClass)
                .filter { it.isLoaded }
                .switchMap { Observable.just(it) } //Not interested in old emission

        //Remote Query is just a side effect
        val remoteQuery = remoteOperation.applySchedulers().doAfterSuccess { localRepo.saveData(it) }

        //Do the remote query only
        /*if (forceUpdate) {
            return remoteQuery
        } else { //Allow cached data*/
        return Observable.defer {
            Observable.create<List<T>>({ subscriber ->
                localQuery.subscribe({subscriber.onNext(it)}, {subscriber.onError(it)})
                remoteQuery.subscribe({}, { subscriber.onError(it) })
            })
        }
        //}
    }

    fun getShowsNextDays(days: Int): Observable<List<CalendarShowEntry>> {
        return remoteRepo.getShowsNextDays(days)
    }

}