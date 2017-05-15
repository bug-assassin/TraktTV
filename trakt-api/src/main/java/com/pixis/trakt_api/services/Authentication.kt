package com.pixis.trakt_api.services

import com.pixis.trakt_api.models.AccessToken
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST



interface Authentication {

    @FormUrlEncoded
    @POST("/oauth/token")
    fun exchangeCodeForAccessToken(@Field("code") code : String,
                                   @Field("client_id") clientId : String,
                                   @Field("client_secret") clientSecret : String,
                                   @Field("redirect_uri") redirectUri : String,
                                   @Field("grant_type") grantType : String = "authorization_code") : Single<AccessToken>

    @FormUrlEncoded
    @POST("/oauth/token")
    fun refreshAccessToken(
            @Field("grant_type") grantType: String = "refresh_token",
            @Field("refresh_token") refreshToken: String,
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("redirect_uri") redirectUri: String
    ): Single<AccessToken>
}