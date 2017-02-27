package com.pixis.trakt_api.models

/**
 * Created by Dan on 11/14/2016.
 */
data class AccessToken(var access_token: String,
                       var token_type: String,
                       var expires_in: Int,
                       var refresh_token: String,
                       var scope: String)