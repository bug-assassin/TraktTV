package com.pixis.traktTV.injection

import com.pixis.traktTV.base.BaseApplication
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Dan on 11/15/2016.
 */
class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var newRequest = chain.request()
        if (BaseApplication.getAccessToken() != null) {
            newRequest = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", BaseApplication.getAccessToken())
                    .build()
        }

        return chain.proceed(newRequest)
    }
}