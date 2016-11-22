package com.pixis.traktTV.views

import android.content.Context
import android.widget.Toast
import com.pixis.traktTV.R

/**
 * Created by Dan on 11/18/2016.
 */
object Utils {
    fun showError(context: Context) {
        //TODO replace with snackbar
        Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
    }

    fun showError(context: Context, message: String) {
        //TODO replace with snackbar
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}