package com.pixis.trakt_api.models

data class AccessToken(var access_token: String,
                       var token_type: String,
                       var expires_in: Int,
                       var refresh_token: String,
                       var scope: String)