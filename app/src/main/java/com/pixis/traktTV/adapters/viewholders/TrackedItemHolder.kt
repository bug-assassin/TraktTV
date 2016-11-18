package com.pixis.traktTV.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.pixis.traktTV.R
import com.pixis.traktTV.data.models.TrackedItem
import com.squareup.picasso.Picasso

/**
 * Created by Dan on 11/9/2016.
 */

class TrackedItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //TV Show Poster
    @BindView(R.id.imgItem)
    lateinit var imgItem: ImageView

    //TVShow Title
    @BindView(R.id.txtTitle)
    lateinit var txtTitle: TextView

    //Season Episode number
    @BindView(R.id.txtSubtitle1)
    lateinit var txtSubtitle1: TextView

    //Episode name
    @BindView(R.id.txtSubtitle2)
    lateinit var txtSubtitle2: TextView

    //Episode Release Date
    @BindView(R.id.txtAdditionalInfo)
    lateinit var txtAdditionalInfo: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(trackedItem: TrackedItem) {
        txtTitle.text = trackedItem.title

        //Episode
        txtSubtitle1.text = trackedItem.episode.episode_number
        txtSubtitle2.text = trackedItem.episode.title
        txtAdditionalInfo.text = trackedItem.episode.release_date

        Picasso.with(itemView.context).load(trackedItem.poster_path).into(imgItem)
    }
}
