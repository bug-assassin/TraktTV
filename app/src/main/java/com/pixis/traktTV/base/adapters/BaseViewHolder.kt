package com.pixis.traktTV.base.adapters

import android.view.View
import butterknife.ButterKnife

/**
 * Created by Dan on 12/5/2016.
 */
abstract class BaseViewHolder<T>() {
    lateinit var itemView: View

    abstract val layoutId: Int

    fun onCreate(itemView: View) {
        this.itemView = itemView
        ButterKnife.bind(this, itemView)
    }

    abstract fun onBind(item: T)
}