package com.pixis.traktTV.views

import android.app.Activity
import android.app.DialogFragment
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.BindString
import butterknife.BindView
import butterknife.ButterKnife
import com.pixis.traktTV.R
import timber.log.Timber

/**
 * Created by Dan on 11/11/2016.
 */
interface AuthDialogResultListener {
    fun onAuthDialogFinished(resultCode : Int, bundle: Bundle?)
}
class AuthDialog(val listener: AuthDialogResultListener) : DialogFragment() {

    @BindView(R.id.webview)
    lateinit var webview : WebView

    @BindView(R.id.webviewProgress)
    lateinit var webviewProgress : View

    @BindString(R.string.client_id)
    lateinit var client_id : String

    @BindString(R.string.redirect_url)
    lateinit var redirect_url : String

    companion object {
        fun newInstance(listener : AuthDialogResultListener) : AuthDialog {
            val authDialog = AuthDialog(listener)
            authDialog.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog)
            return authDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Remove the default background
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        var v = inflater.inflate(R.layout.auth_dialog, container, false)
        v.findViewById(R.id.popup_root).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view)

        val url = String.format("https://trakt.tv/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s", client_id, redirect_url)
        webview.getSettings().setJavaScriptEnabled(true)
        webview.loadUrl(url)
        webview.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webviewProgress.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webviewProgress.hide()
                if(url != null && url.contains("/authorize/")) {
                    val code = url.substring(url.indexOf("authorize/") + 10)
                    val returnBundle = Bundle()
                    returnBundle.putString("CODE", code)
                    setResult(Activity.RESULT_OK, returnBundle)
                }
                Timber.d("URL %s", url)
            }
        })
    }


    private fun setResult(result: Int, bundle: Bundle? = null) {
        listener.onAuthDialogFinished(result, bundle)
        dismiss()
    }
}