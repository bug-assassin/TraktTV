package com.pixis.trakt_api.Token

import com.pixis.trakt_api.network_models.AccessToken

/**
 * Created by Dan on 11/16/2016.
 */
interface TokenStorage {
    fun isAuthenticated(): Boolean
    fun getAccessToken(): String
    fun getToken(): AccessToken
    fun saveToken(accessToken: AccessToken)
}