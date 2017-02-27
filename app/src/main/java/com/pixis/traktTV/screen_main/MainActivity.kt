package com.pixis.traktTV.screen_main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import com.pixis.traktTV.BuildConfig
import com.pixis.traktTV.R
import com.pixis.traktTV.adapters.SingleItemAdapter
import com.pixis.traktTV.adapters.viewholders.CalendarShowsViewHolder
import com.pixis.traktTV.base.BaseRxActivity
import com.pixis.traktTV.screen_login.LoginActivity
import com.pixis.traktTV.screen_main.presenters.PresenterMainActivity
import com.pixis.traktTV.views.AdvancedRecyclerView
import com.pixis.trakt_api.Token.TokenDatabase
import com.pixis.trakt_api.models.CalendarShowEntry
import nucleus.factory.RequiresPresenter
import rx.Observable
import timber.log.Timber
import javax.inject.Inject


//List of tracked movies and tv shows
@RequiresPresenter(PresenterMainActivity::class)
class MainActivity : BaseRxActivity<PresenterMainActivity>() {

    override val layoutId: Int = R.layout.activity_main

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: AdvancedRecyclerView
    @BindView(R.id.fabMainAction)
    lateinit var fabMainAction: FloatingActionButton
    lateinit var trackedItemAdapter: SingleItemAdapter<CalendarShowEntry>

    @Inject
    lateinit var tokenDatabase: TokenDatabase


    override fun injectPresenter(presenter: PresenterMainActivity) {
        getComponent().inject(presenter)
    }

    override fun init(savedInstanceState: Bundle?) {
        getComponent().inject(this)

        if (!tokenDatabase.isAuthenticated()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        trackedItemAdapter = SingleItemAdapter(this, null, CalendarShowsViewHolder())
        recyclerView.setAdapter(trackedItemAdapter)
        recyclerView.setRefreshing(true)

        if (BuildConfig.DEBUG) {
            Timber.d("ACCESS TOKEN %s", tokenDatabase.getAccessToken())
        }

        recyclerView.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { loadData(fromSwipe = true) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            loadData()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadData(fromSwipe: Boolean = false) {
        if (!fromSwipe)
            recyclerView.setRefreshing(true)

        presenter.loadCalendar()
    }

    fun setData(it: List<CalendarShowEntry>) {
        trackedItemAdapter.setItems(it)//TODO
        recyclerView.setRefreshing(false)
    }

    fun showError(s: String) {
        Snackbar.make(recyclerView, s, Snackbar.LENGTH_SHORT).show()
        recyclerView.setRefreshing(false)
    }
}
