package com.pixis.traktTV.screen_movies_detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import com.pixis.traktTV.base.BaseApplication
import com.pixis.traktTV.base.Repository
import javax.inject.Inject

class MovieDetailViewModel(mApplication: Application): AndroidViewModel(mApplication) {

    @Inject
    lateinit var repo: Repository
    var injected = false
    fun bindView() {
        if(!injected) {
            Log.d("Y", "Injection")
            getApplication<BaseApplication>().component.inject(this)
        }
        Log.d("Y", "BindView")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("Y", "Cleared")
    }
}