package com.pixis.traktTV.base

import android.os.Bundle

/**
 * Created by Dan on 11/14/2016.
 */
interface ActivityFragmentCommon {
    val layoutId : Int

    fun init(savedInstanceState : Bundle?)
}