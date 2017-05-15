package com.pixis.trakt_api.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rx.functions.Func2

fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T1, T2> asPair() = Func2<T1, T2, Pair<T1, T2>> { t1, t2 ->
    return@Func2 Pair(t1, t2)
}