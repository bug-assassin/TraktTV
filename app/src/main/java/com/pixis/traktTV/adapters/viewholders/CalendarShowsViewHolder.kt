package com.pixis.traktTV.adapters.viewholders

import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.pixis.traktTV.R
import com.pixis.traktTV.base.adapters.BaseViewHolder
import com.pixis.traktTV.data.models.TrackedItem
import com.pixis.trakt_api.models.CalendarShowEntry
import com.squareup.picasso.Picasso

/**
 * Created by Dan on 11/9/2016.
 */

class CalendarShowsViewHolder : BaseViewHolder<CalendarShowEntry>() {

    //TV Show Poster
    @BindView(R.id.imgItem) lateinit var imgItem: ImageView
    //TVShow Title
    @BindView(R.id.txtTitle) lateinit var txtTitle: TextView
    //Season Episode number
    @BindView(R.id.txtSubtitle1) lateinit var txtSubtitle1: TextView
    //Episode name
    @BindView(R.id.txtSubtitle2) lateinit var txtSubtitle2: TextView
    //Episode Release Date
    @BindView(R.id.txtAdditionalInfo) lateinit var txtAdditionalInfo: TextView

    override val layoutId: Int = R.layout.watchlist_item

    override fun onBind(trackedItem: CalendarShowEntry) {
        txtTitle.text = trackedItem.show.title

        //Episode
        txtSubtitle1.text = trackedItem.episode.number.toString()
        txtSubtitle2.text = trackedItem.episode.title
        txtAdditionalInfo.text = trackedItem.episode.release_date

        //Picasso.with(itemView.context).load(trackedItem.poster_path).into(imgItem)
    }
}
