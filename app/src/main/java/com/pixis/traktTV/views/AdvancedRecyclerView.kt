package com.pixis.traktTV.views

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

import com.pixis.traktTV.R

/**
 * Created by Dan on 11/9/2016.
 */

open class AdvancedRecyclerView : LinearLayout {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: View
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private var isDataView = true

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    internal fun init() {
        if (isInEditMode) {
            return
        }

        val v = LayoutInflater.from(context).inflate(R.layout.advanced_recyclerview, this, false)
        recyclerView = v.findViewById(R.id.advanced_recyclerview_recyclerView) as RecyclerView
        emptyView = v.findViewById(R.id.advanced_recyclerview_emptyView)
        swipeRefreshLayout = v.findViewById(R.id.advanced_recyclerview_refreshContainer) as SwipeRefreshLayout

        //Init recyclerview
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        addView(v)
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                if (adapter.itemCount == 0) {
                    showEmptyView()
                } else {
                    showDataView()
                }
            }
        })
    }

    fun setOnRefreshListener(onRefreshListener: SwipeRefreshLayout.OnRefreshListener) {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener)
    }

    private fun showEmptyView() {
        if (isDataView) {
            emptyView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            isDataView = false
        }
    }

    private fun showDataView() {
        if (!isDataView) {
            emptyView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            isDataView = true
        }
    }

    fun setRefreshing(refreshing : Boolean) {
        swipeRefreshLayout.isRefreshing = refreshing
    }

}
