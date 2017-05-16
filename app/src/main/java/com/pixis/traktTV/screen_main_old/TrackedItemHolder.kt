package com.pixis.traktTV.screen_main_old

import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.pixis.traktTV.R
import com.pixis.traktTV.base.adapters.BaseViewHolder
import com.squareup.picasso.Picasso
/*
class TrackedItemHolder : BaseViewHolder<TrackedItem>() {

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

    override fun onBind(item: TrackedItem) {
        txtTitle.text = item.title

        //Episode TODO
        /*txtSubtitle1.text = item.episode.number.toString()
        txtSubtitle2.text = item.episode.title
        txtAdditionalInfo.text = item.episode.release_date*/

        Picasso.with(itemView.context).load(item.poster_path).into(imgItem)
    }
}
*/