package com.pixis.traktTV

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import butterknife.BindView
import com.pixis.traktTV.adapters.TrackedItemAdapter
import com.pixis.traktTV.base.BaseActivity
import com.pixis.traktTV.data.models.TrackedItem
import com.pixis.traktTV.views.AdvancedRecyclerView
import com.pixis.trakt_api.Token.TokenDatabase
import com.pixis.trakt_api.services.Sync
import com.pixis.trakt_api.utils.applySchedulers
import rx.Observable
import javax.inject.Inject

//List of tracked movies and tv shows
class MainActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_main

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: AdvancedRecyclerView

    lateinit var trackedItemAdapter: TrackedItemAdapter

    @BindView(R.id.fabMainAction)
    lateinit var fabMainAction: FloatingActionButton

    @Inject
    lateinit var tokenDatabase: TokenDatabase

    @Inject
    lateinit var syncService: Sync

    override fun init(savedInstanceState: Bundle?) {
        getComponent().inject(this)

        trackedItemAdapter = TrackedItemAdapter(this, null)
        recyclerView.setAdapter(trackedItemAdapter)

        if (!tokenDatabase.isAuthenticated()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        else {
            loadData();
        }
        fabMainAction.setOnClickListener { loadData() }
    }

    private fun loadData() {
        syncService.getWatchListShows()
                .flatMap {
                    Observable.from(it).map({ show -> TrackedItem(show.show.title) }).toList()
                }
                .applySchedulers()
                .subscribe {
                    trackedItemAdapter.setData(it)
                }
    }

}
