package com.pixis.traktTV.screen_main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.pixis.traktTV.R
import com.pixis.traktTV.adapters.SingleItemAdapter
import com.pixis.traktTV.base.BaseApplication
import com.pixis.traktTV.base.Repository
import com.pixis.traktTV.base.adapters.BaseViewHolder
import com.pixis.traktTV.base.adapters.BindableViewHolder
import com.pixis.trakt_api.services_fanart.FanArtImages
import com.pixis.trakt_api.services_trakt.models.Show
import com.pixis.trakt_api.utils.applySchedulers
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ShowsFragment: Fragment() {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: ShowAdapter

    @Inject
    lateinit var repo: Repository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_main_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ButterKnife.bind(this, view)

        mAdapter = ShowAdapter(Picasso.with(activity))
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = mAdapter

        (activity.applicationContext as BaseApplication).component.inject(this)

        //TODO Move to presenter
        repo.getTrendingShows().subscribe ({
            setData(it)
        },
                {
                    it.printStackTrace()
                })

    }

    private fun  showError(it: Throwable) {
        Toast.makeText(activity, it.message, Toast.LENGTH_SHORT)
    }

    fun setData(data: List<Pair<Show, FanArtImages>>) {
        mAdapter.data = data
        mAdapter.notifyDataSetChanged()
    }

}
class ShowAdapter(val picasso: Picasso): RecyclerView.Adapter<ShowViewHolder>() {
    var data: List<Pair<Show, FanArtImages>> = emptyList()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.common_poster_summary, parent, false)
        return ShowViewHolder(v, picasso)
    }

}
class ShowViewHolder(itemView: View, val picasso: Picasso): RecyclerView.ViewHolder(itemView) {

    init {
        ButterKnife.bind(this, itemView)
    }

    @BindView(R.id.imgMediaItem) lateinit var imgMediaItem: ImageView
    @BindView(R.id.lblMediaTitle) lateinit var lblMediaTitle: TextView

    fun onBind(item: Pair<Show, FanArtImages>) {
        lblMediaTitle.text = item.first.title
        if(item.second.tvposter.isNotEmpty()) {
            picasso.load(item.second.tvposter.first().getPreviewUrl()).placeholder(android.R.color.darker_gray).fit().into(imgMediaItem)
        }
        else {
            imgMediaItem.setImageResource(android.R.color.darker_gray)
        }
    }

}