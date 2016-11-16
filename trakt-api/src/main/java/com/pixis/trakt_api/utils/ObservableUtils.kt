package com.pixis.trakt_api.utils

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func2

/**
 * Created by Dan on 11/15/2016.
 */
fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return this.subscribeOn(rx.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T1, T2> asPair() = Func2<T1, T2, Pair<T1, T2>> { t1, t2 ->
    return@Func2 Pair(t1, t2)
}