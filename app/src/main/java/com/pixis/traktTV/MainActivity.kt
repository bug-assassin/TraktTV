package com.pixis.traktTV

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import com.pixis.traktTV.adapters.TrackedItemAdapter
import com.pixis.traktTV.base.BaseActivity
import com.pixis.traktTV.data.models.TrackedItem
import com.pixis.traktTV.repository.LocalRepository
import com.pixis.traktTV.repository.RemoteRepository
import com.pixis.traktTV.repository.Repository
import com.pixis.traktTV.views.AdvancedRecyclerView
import com.pixis.traktTV.views.Utils
import com.pixis.trakt_api.Token.TokenDatabase
import io.realm.Realm
import rx.Observable
import rx.Subscription
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

//List of tracked movies and tv shows
class MainActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_main

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: AdvancedRecyclerView
    @BindView(R.id.fabMainAction)
    lateinit var fabMainAction: FloatingActionButton
    lateinit var trackedItemAdapter: TrackedItemAdapter

    @Inject
    lateinit var tokenDatabase: TokenDatabase

    @Inject
    lateinit var remoteRepository: RemoteRepository

    //TODO move to dagger
    lateinit var localRepository: LocalRepository
    lateinit var repository: Repository
    lateinit var mRealm: Realm

    var request: Observable<List<TrackedItem>>? = null
    var subscription: Subscription? = null

    override fun init(savedInstanceState: Bundle?) {
        getComponent().inject(this)

        if (!tokenDatabase.isAuthenticated()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        mRealm = Realm.getDefaultInstance()
        localRepository = LocalRepository(mRealm)
        repository = Repository(remoteRepository, localRepository)

        trackedItemAdapter = TrackedItemAdapter(this, null)
        recyclerView.setAdapter(trackedItemAdapter)

        if (BuildConfig.DEBUG) {
            Timber.d("ACCESS TOKEN %s", tokenDatabase.getAccessToken())
        }

        recyclerView.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { loadData(forceUpdate = true) })

        //Load initial Data
        loadDataShowRefreshing()
    }

    private fun loadDataShowRefreshing() {
        recyclerView.setRefreshing(true)
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            loadDataShowRefreshing()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadData(forceUpdate: Boolean = false) {
        subscription?.unsubscribe()
        request = repository.getWatchList(forceUpdate)

        subscription = request
                ?.doOnNext { recyclerView.setRefreshing(false) }
                ?.doAfterTerminate { recyclerView.setRefreshing(false) }
                ?.subscribe({
                    trackedItemAdapter.setData(it)
                }, { error ->
                    if(error is IOException) {
                        Utils.showError(getContext(), "No Internet Connection")
                    }
                    else {
                        Utils.showError(getContext())
                        Timber.e(error)
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
        subscription?.unsubscribe()
    }
}
