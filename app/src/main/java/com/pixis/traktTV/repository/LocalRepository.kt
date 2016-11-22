package com.pixis.traktTV.repository

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults
import rx.Observable

/**
 * Created by Dan on 11/20/2016.
 */
class LocalRepository(private val mRealm: Realm) {
    fun <T: RealmModel> getItems(realmClass: Class<T>): Observable<RealmResults<T>> {
        return mRealm.where(realmClass).findAllAsync().asObservable()
    }

    fun <T: RealmModel> saveData(items: List<T>) {
        mRealm.executeTransactionAsync { realm -> realm.copyToRealmOrUpdate(items) }
    }
}