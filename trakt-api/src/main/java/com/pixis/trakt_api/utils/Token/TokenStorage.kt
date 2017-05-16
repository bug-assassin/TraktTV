package com.pixis.trakt_api.utils.Token

import com.pixis.trakt_api.services_trakt.models.AccessToken

interface TokenStorage {
    fun isAuthenticated(): Boolean
    fun getAccessToken(): String
    fun getToken(): AccessToken
    fun saveToken(accessToken: AccessToken)
}