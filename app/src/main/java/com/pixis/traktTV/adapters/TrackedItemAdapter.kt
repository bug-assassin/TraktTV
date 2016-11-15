package com.pixis.traktTV.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pixis.traktTV.R
import com.pixis.traktTV.adapters.viewholders.TrackedItemHolder
import com.pixis.traktTV.data.models.TrackedItem
import java.util.*

/**
 * Created by Dan on 11/9/2016.
 */

interface onItemClickListener {
    fun onTrackedItemClicked(item: TrackedItem, viewHolder: TrackedItemHolder, position: Int)
    fun onTrackedItemLongClick(item: TrackedItem, viewHolder: TrackedItemHolder, position: Int)
}

class TrackedItemAdapter(private val mContext: Context, private val listener: onItemClickListener?) : RecyclerView.Adapter<TrackedItemHolder>() {

    internal var mData: List<TrackedItem>

    init {
        mData = ArrayList<TrackedItem>()
    }

    fun setData(data: List<TrackedItem>) {
        this.mData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackedItemHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.watchlist_item, parent, false)
        return TrackedItemHolder(v)
    }

    override fun onBindViewHolder(holder: TrackedItemHolder, position: Int) {
        val mTrackedItem = mData[position]
        holder.bind(mTrackedItem)

        holder.itemView.setOnLongClickListener {
            listener?.onTrackedItemLongClick(mTrackedItem, holder, position)
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener {
            listener?.onTrackedItemClicked(mTrackedItem, holder, position)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}
