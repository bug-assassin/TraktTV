package com.pixis.trakt_api.utils

import rx.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by Dan on 11/15/2016.
 */
fun <T> Observable<T>.applySchedulers() : Observable<T> {
    return this.subscribeOn(rx.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}