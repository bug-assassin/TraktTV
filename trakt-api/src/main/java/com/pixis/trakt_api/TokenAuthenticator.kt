package com.pixis.trakt_api

import com.pixis.trakt_api.network_models.AccessToken
import com.pixis.trakt_api.services.Authentication
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Created by Dan on 11/15/2016.
 */
class TokenAuthenticator(val authenticationService: Authentication, val refreshToken: String, val client_id: String, val client_secret: String, val redirect_url: String) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        val refreshToken: AccessToken = authenticationService.refreshAccessToken(
                refreshToken = refreshToken,
                clientId = client_id,
                clientSecret = client_secret,
                redirectUri = redirect_url)
                .toBlocking()
                .first()

        return response.request()
                .newBuilder()
                .header("Authorization", "Bearer " + refreshToken.access_token)
                .build()
    }
}