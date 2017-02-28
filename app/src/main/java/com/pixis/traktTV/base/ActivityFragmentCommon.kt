package com.pixis.traktTV.base

import android.os.Bundle

interface ActivityFragmentCommon {
    val layoutId : Int

    fun init(savedInstanceState : Bundle?)
}