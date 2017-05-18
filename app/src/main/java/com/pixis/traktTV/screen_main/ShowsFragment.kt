package com.pixis.traktTV.screen_main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.pixis.traktTV.R
import com.pixis.traktTV.adapters.RecyclerListAdapter
import com.pixis.traktTV.base.BaseApplication
import com.pixis.traktTV.base.Repository
import com.pixis.traktTV.base.adapters.BaseViewHolder
import com.pixis.trakt_api.services_fanart.FanArtImages
import com.pixis.trakt_api.services_trakt.models.Show
import com.squareup.picasso.Picasso
import io.reactivex.functions.Consumer
import javax.inject.Inject

class ShowsFragment: Fragment() {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: RecyclerListAdapter<Pair<Show, FanArtImages>>

    @Inject
    lateinit var repo: Repository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_main_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ButterKnife.bind(this, view)

        (activity.applicationContext as BaseApplication).component.inject(this)
        mAdapter = RecyclerListAdapter<Pair<Show, FanArtImages>>(ShowViewHolder(Picasso.with(activity)))
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = mAdapter

        //TODO Move to presenter
        repo.getTrendingShows().subscribe(getConsumer(), Consumer<Throwable>{ showError(it) })
        mAdapter.clickObservable.subscribe {
            Log.v("Click", "Click " + it.item.first.title + " - " + it.isLongClick)
        }
    }

    fun getConsumer() = mAdapter.consumer //So we can mock this

    fun  showError(it: Throwable) {
        Snackbar.make(activity.findViewById(R.id.coordinator_main_content), it.message.toString(), Snackbar.LENGTH_SHORT)
                .setAction("Retry", {
                }).show()
    }

}
class ShowViewHolder(val picasso: Picasso): BaseViewHolder<Pair<Show, FanArtImages>>() {
    override val layoutId = R.layout.common_poster_summary

    @BindView(R.id.imgMediaItem) lateinit var imgMediaItem: ImageView
    @BindView(R.id.lblMediaTitle) lateinit var lblMediaTitle: TextView

    override fun onBind(item: Pair<Show, FanArtImages>) {
        lblMediaTitle.text = item.first.title
        if(item.second.tvposter.isNotEmpty()) {
            picasso.load(item.second.tvposter.first().getPreviewUrl()).placeholder(android.R.color.darker_gray).fit().into(imgMediaItem)
        }
        else {
            imgMediaItem.setImageResource(android.R.color.darker_gray)
        }
    }

}