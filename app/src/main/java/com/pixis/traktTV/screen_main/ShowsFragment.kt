package com.pixis.traktTV.screen_main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.pixis.traktTV.R
import com.pixis.traktTV.adapters.SingleItemAdapter
import com.pixis.traktTV.base.adapters.BaseViewHolder
import com.pixis.traktTV.base.adapters.BindableViewHolder
import com.pixis.trakt_api.services_fanart.FanArtImages
import com.pixis.trakt_api.services_trakt.models.Show
import com.pixis.trakt_api.services_trakt.models.TrendingShow
import com.squareup.picasso.Picasso

class ShowsFragment: Fragment() {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SingleItemAdapter<Pair<Show, FanArtImages>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_main_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ButterKnife.bind(this, view)

        adapter = SingleItemAdapter(ShowViewHolder(Picasso.with(activity)))
        recyclerView.adapter = adapter

        //TODO Move to presenter

    }

    fun setData(data: List<Pair<Show, FanArtImages>>) {
        adapter.update(data)
    }

}
class ShowViewHolder(val picasso: Picasso): BaseViewHolder<Pair<Show, FanArtImages>>() {
    override val layoutId = R.layout.common_poster_summary

    @BindView(R.id.imgMediaItem) lateinit var imgMediaItem: ImageView
    @BindView(R.id.lblMediaTitle) lateinit var lblMediaTitle: TextView

    override fun onBind(item: Pair<Show, FanArtImages>) {
        lblMediaTitle.text = item.first.title
        picasso.load(item.second.tvposter.first().url)
    }

}