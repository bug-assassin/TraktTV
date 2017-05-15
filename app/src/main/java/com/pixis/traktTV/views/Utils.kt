package com.pixis.traktTV.views

import android.content.Context
import android.widget.Toast
import com.pixis.traktTV.R
import io.reactivex.Observable
import io.realm.RealmResults
import io.realm.RealmChangeListener
import io.reactivex.ObservableEmitter
import io.realm.RealmObject
import io.reactivex.ObservableOnSubscribe
import io.realm.RealmModel


object Utils {
    fun showError(context: Context) {
        //TODO replace with snackbar
        Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
    }

    fun showError(context: Context, message: String) {
        //TODO replace with snackbar
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

//From https://gist.github.com/patloew/642cf595b143cd9d600579ca988b616a
class RealmResultsObservable<T : RealmModel>(private val realmResults: RealmResults<T>) : ObservableOnSubscribe<RealmResults<T>> {

    override fun subscribe(emitter: ObservableEmitter<RealmResults<T>>) {
        // Initial element
        emitter.onNext(realmResults)

        val changeListener = RealmChangeListener<RealmResults<T>> { element -> emitter.onNext(element) }

        realmResults.addChangeListener(changeListener)

        emitter.setCancellable{realmResults.removeChangeListener(changeListener)}
    }

}
fun <T : RealmModel> RealmResults<T>.asObservable2(): Observable<RealmResults<T>> {
    return Observable.create(RealmResultsObservable(this))
}