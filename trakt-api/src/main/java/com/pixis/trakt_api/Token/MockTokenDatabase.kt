package com.pixis.trakt_api.Token

import com.pixis.trakt_api.models.AccessToken

class MockTokenDatabase(val mAccessToken : String) : TokenStorage {

    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun getAccessToken(): String {
        return mAccessToken
    }

    override fun getToken(): AccessToken {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveToken(accessToken: AccessToken) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}