package com.pixis.trakt_api.Token

import com.pixis.trakt_api.models.AccessToken

interface TokenStorage {
    fun isAuthenticated(): Boolean
    fun getAccessToken(): String
    fun getToken(): AccessToken
    fun saveToken(accessToken: AccessToken)
}