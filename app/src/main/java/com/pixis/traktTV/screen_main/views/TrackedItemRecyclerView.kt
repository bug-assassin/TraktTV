package com.pixis.traktTV.screen_main.views

import android.content.Context
import android.util.AttributeSet
import com.pixis.traktTV.views.AdvancedRecyclerView
import javax.inject.Inject

/**
 * Created by Dan on 11/23/2016.
 */
class TrackedItemRecyclerView: AdvancedRecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    @Inject

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

    }
}