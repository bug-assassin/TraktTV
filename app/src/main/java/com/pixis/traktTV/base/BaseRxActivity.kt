package com.pixis.traktTV.base

import android.os.Bundle
import nucleus5.factory.PresenterFactory
import nucleus5.factory.ReflectionPresenterFactory
import nucleus5.presenter.Presenter
import nucleus5.view.PresenterLifecycleDelegate
import nucleus5.view.ViewWithPresenter
/*
/**
 * From NucleusAppCompatActivity
 */
abstract class BaseRxActivity<P : Presenter<*>> : BaseActivity(), ViewWithPresenter<P> {

    private val PRESENTER_STATE_KEY = "presenter_state"

    private val presenterDelegate: PresenterLifecycleDelegate<P> = PresenterLifecycleDelegate(null)

    /**
     * Returns a current presenter factory.
     */
    override fun getPresenterFactory(): PresenterFactory<P> {
        return presenterDelegate.presenterFactory!!
    }

    /**
     * Sets a presenter factory.
     * Call this method before onCreate/onFinishInflate to override default [ReflectionPresenterFactory] presenter factory.
     * Use this method for presenter dependency injection.
     */
    override fun setPresenterFactory(presenterFactory: PresenterFactory<P>) {
        presenterDelegate.presenterFactory = presenterFactory
    }

    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onResume/onPause and onAttachedToWindow/onDetachedFromWindow calls
     * if the presenter factory returns a non-null value.

     * @return a currently attached presenter or null.
     */
    override fun getPresenter(): P {
        return presenterDelegate.presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val superFactory = ReflectionPresenterFactory.fromViewClass<P>(javaClass) ?: throw RuntimeException("Did not call @RequiresPresenter")
        presenterFactory = PresenterFactory<P> {
            val presenter = superFactory.createPresenter()
            injectPresenter(presenter)
            presenter
        }
        if (savedInstanceState != null)
            presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_STATE_KEY))

        super.onCreate(savedInstanceState)
    }

    open fun injectPresenter(presenter: P) {
        //Override to actually inject
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState())
    }

    override fun onResume() {
        super.onResume()
        presenterDelegate.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        presenterDelegate.onDropView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterDelegate.onDestroy(!isChangingConfigurations)
    }
}*/