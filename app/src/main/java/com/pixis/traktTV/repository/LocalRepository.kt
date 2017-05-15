package com.pixis.traktTV.repository

import com.pixis.traktTV.views.RealmResultsObservable
import com.pixis.traktTV.views.asObservable2
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults

class LocalRepository(private val mRealm: Realm) {
    fun <T: RealmModel> getItems(realmClass: Class<T>): Observable<RealmResults<T>> {
        return mRealm.where(realmClass).findAllAsync().asObservable2()
    }

    fun <T: RealmModel> saveData(items: List<T>) {
        mRealm.executeTransactionAsync { realm -> realm.copyToRealmOrUpdate(items) }
    }
}