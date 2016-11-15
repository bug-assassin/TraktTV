package com.pixis.traktTV.views

import android.view.View

/**
 * Created by Dan on 11/11/2016.
 */
fun View.show() {
    this.visibility = View.VISIBLE
}
fun View.hide() {
    this.visibility = View.GONE
}

fun View.show(showView: Boolean) {
    if(showView) {
        show();
    }
    else {
        hide();
    }
}