package com.pixis.traktTV.screen_login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import butterknife.BindString
import butterknife.OnClick
import com.pixis.traktTV.R
import com.pixis.traktTV.base.BaseActivity
import com.pixis.traktTV.screen_main.MainActivity
import com.pixis.traktTV.screen_login.views.AuthDialog
import com.pixis.traktTV.screen_login.views.AuthDialogResultListener
import com.pixis.trakt_api.Token.TokenDatabase
import com.pixis.trakt_api.services.Authentication
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class LoginActivity : BaseActivity() {
    override val layoutId = R.layout.activity_login

    @Inject
    lateinit var auth: Authentication

    @Inject
    lateinit var tokenDatabase: TokenDatabase

    @BindString(R.string.client_id)
    lateinit var client_id: String

    @BindString(R.string.client_secret)
    lateinit var client_secret: String

    @BindString(R.string.redirect_url)
    lateinit var redirect_url: String

    override fun init(savedInstanceState: Bundle?) {
        getComponent().inject(this)
    }

    @OnClick(R.id.btnLogin)
    fun login() {
        AuthDialog.newInstance(object : AuthDialogResultListener {
            override fun onAuthDialogFinished(resultCode: Int, bundle: Bundle?) {
                if (resultCode == Activity.RESULT_OK) {
                    authenticate(bundle!!.getString("CODE"))
                } else {
                    Toast.makeText(getContext(), "An error has occurred", Toast.LENGTH_SHORT)
                }
            }
        }).show(fragmentManager, null)
    }

    private fun authenticate(code: String) {
        auth.exchangeCodeForAccessToken(code = code,
                clientId = client_id,
                clientSecret = client_secret,
                redirectUri = redirect_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .first()
                .filter { it != null }
                .subscribe {
                    tokenDatabase.saveToken(it)
                    Timber.d("ACCESS TOKEN %s", it.toString())
                    onLoginFinished()
                }
    }

    private fun onLoginFinished() {
        val intent = Intent(getContext(), MainActivity::class.java)
                .withBackStackCleared()
        startActivity(intent)
        finish()
    }

}

//TODO extract this
private fun Intent.withBackStackCleared(): Intent {
    return this.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
}
