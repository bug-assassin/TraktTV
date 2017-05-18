package com.pixis.traktTV.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pixis.traktTV.base.adapters.BaseViewHolder
import com.pixis.traktTV.base.adapters.BindableViewHolder
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

interface OnItemClickListener<T> {
    fun onTrackedItemClicked(item: T, viewHolder: BindableViewHolder<T>, position: Int)
    fun onTrackedItemLongClick(item: T, viewHolder: BindableViewHolder<T>, position: Int)
}

data class ClickEvent<T>(val item: T, val view: BaseViewHolder<T>, val position: Int, val isLongClick: Boolean)

class RecyclerListAdapter<T>(private val viewHolder: BaseViewHolder<T>) : RecyclerView.Adapter<BindableViewHolder<T>>() {

    private var mData: List<T> = emptyList()

    var clickObservable: PublishSubject<ClickEvent<T>> = PublishSubject.create<ClickEvent<T>>()

    var consumer: Consumer<in List<T>> = Consumer {
        mData = it
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T> {
        val v = LayoutInflater.from(parent.context).inflate(viewHolder.layoutId, parent, false)
        return BindableViewHolder<T>(v, viewHolder)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        val mTrackedItem = mData[position]
        holder.onBind(mTrackedItem)

        holder.itemView.setOnLongClickListener {
            clickObservable.onNext(ClickEvent(mTrackedItem, holder.baseViewHolder, position, isLongClick = true))
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener {
            clickObservable.onNext(ClickEvent(mTrackedItem, holder.baseViewHolder, position, isLongClick = false))
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}
