package com.pixis.trakt_api.services_trakt.models

data class AccessToken(var access_token: String,
                       var token_type: String,
                       var expires_in: Int,
                       var refresh_token: String,
                       var created_at: String,
                       var scope: String)