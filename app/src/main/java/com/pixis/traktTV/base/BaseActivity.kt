package com.pixis.traktTV.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.pixis.traktTV.injection.ApplicationComponent


abstract class BaseActivity: AppCompatActivity(), ActivityFragmentCommon {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        ButterKnife.bind(this)
        init(savedInstanceState)
    }

    override fun init(savedInstanceState: Bundle?) {
        //Default implementation does nothing
    }

    fun getComponent(): ApplicationComponent {
        return (applicationContext as BaseApplication).component
    }

    fun getContext(): Context {
        return this
    }

}