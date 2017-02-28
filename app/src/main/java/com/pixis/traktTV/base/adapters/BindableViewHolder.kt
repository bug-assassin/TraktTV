package com.pixis.traktTV.base.adapters

import android.support.v7.widget.RecyclerView
import android.view.View

class BindableViewHolder<in T>(itemView: View, val baseViewHolder: BaseViewHolder<in T>) : RecyclerView.ViewHolder(itemView) {

    init {
        baseViewHolder.onCreate(itemView)
    }

    fun onBind(item: T) {
        baseViewHolder.onBind(item)
    }
}