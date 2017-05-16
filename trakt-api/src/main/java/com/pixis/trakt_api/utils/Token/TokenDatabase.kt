package com.pixis.trakt_api.utils.Token

import android.content.Context
import android.content.SharedPreferences
import com.pixis.trakt_api.services_trakt.models.AccessToken

class TokenDatabase(val context: Context) : TokenStorage {

    val prefs : SharedPreferences

    init {
        prefs = context.getSharedPreferences("TRAKT_ACCESS_TOKEN", Context.MODE_PRIVATE)
    }

    companion object {
        private val ACCESS_TOKEN_KEY: String = "ACCESS_TOKEN_KEY"
        private val TOKEN_TYPE_KEY: String = "TOKEN_TYPE_KEY"
        private val EXPIRES_IN_KEY: String = "EXPIRES_IN_KEY"
        private val REFRESH_TOKEN_KEY: String = "REFRESH_TOKEN_KEY"
        private val CREATED_AT: String = "CREATED_AT"
        private val SCOPE_KEY: String = "SCOPE_KEY"
    }

    override fun isAuthenticated(): Boolean {
        return prefs.getBoolean("AUTHENTICATED", false)
    }

    override fun getAccessToken(): String {
        return prefs.getString(ACCESS_TOKEN_KEY, "")
    }

    override fun getToken(): AccessToken {
        return AccessToken(
                prefs.getString(ACCESS_TOKEN_KEY, ""),
                prefs.getString(TOKEN_TYPE_KEY, ""),
                prefs.getString(EXPIRES_IN_KEY, "0").toInt(),
                prefs.getString(REFRESH_TOKEN_KEY, ""),
                prefs.getString(CREATED_AT, ""),
                prefs.getString(SCOPE_KEY, "")
        )
    }

    override fun saveToken(accessToken: AccessToken) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN_KEY, accessToken.access_token)
        editor.putString(TOKEN_TYPE_KEY, accessToken.token_type)
        editor.putString(EXPIRES_IN_KEY, accessToken.expires_in.toString())
        editor.putString(REFRESH_TOKEN_KEY, accessToken.refresh_token)
        editor.putString(CREATED_AT, accessToken.created_at)
        editor.putString(SCOPE_KEY, accessToken.scope)
        editor.putBoolean("AUTHENTICATED", true)
        editor.apply()
    }

}