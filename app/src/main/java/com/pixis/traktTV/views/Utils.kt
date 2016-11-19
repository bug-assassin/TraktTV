package com.pixis.traktTV.views

import android.content.Context
import android.widget.Toast
import com.pixis.traktTV.R
import timber.log.Timber

/**
 * Created by Dan on 11/18/2016.
 */
object Utils {
    fun showAndLogError(context: Context, error: Throwable) {
        //TODO replace with snackbar
        Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
        Timber.e(error)
    }
}