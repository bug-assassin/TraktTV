package com.pixis.traktTV.screen_main.presenters

import android.os.Bundle
import com.pixis.traktTV.base.BasePresenter
import com.pixis.traktTV.repository.LocalRepository
import com.pixis.traktTV.repository.RemoteRepository
import com.pixis.traktTV.repository.Repository
import com.pixis.traktTV.screen_main.MainActivity
import io.realm.Realm
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Dan on 11/26/2016.
 */
class PresenterMainActivity : BasePresenter<MainActivity>() {
    @Inject
    lateinit var remoteRepository: RemoteRepository

    //TODO move to dagger
    var localRepository: LocalRepository? = null
    lateinit var repository: Repository

    @Inject
    lateinit var mRealm: Realm

    val GET_CALENDAR = 0

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)

        localRepository = LocalRepository(mRealm)
        repository = Repository(remoteRepository, localRepository!!)

        restartableLatestCache(GET_CALENDAR,
                {
                    repository.getShowsNextDays(3)
                },
                MainActivity::setData,
                { view, error ->
                    if (error is IOException) {
                        view.showError("No Internet Connection")
                    } else {
                        view.showError("An internal error has occured")
                        Timber.e(error)
                    }
                }
        )

        loadCalendar()
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }

    fun loadCalendar() {
        start(GET_CALENDAR)
    }
}