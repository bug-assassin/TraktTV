package com.pixis.traktTV.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.pixis.traktTV.R
import com.pixis.traktTV.data.models.TrackedItem

/**
 * Created by Dan on 11/9/2016.
 */

class TrackedItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.imgItem)
    lateinit var imgItem: ImageView

    @BindView(R.id.txtTitle)
    lateinit var txtTitle: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(trackedItem: TrackedItem) {
        txtTitle.text = trackedItem.title
    }
}
