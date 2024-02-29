package com.arshapshap.wallette.core.network.data.services

import com.arshapshap.wallette.core.network.data.models.request.LoginRequestModel
import com.arshapshap.wallette.core.network.data.models.request.RegisterRequestModel
import com.arshapshap.wallette.core.network.data.models.response.AuthorizationResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthorizationApiService {

    @Headers(com.arshapshap.wallette.core.network.data.NetworkConstants.NO_TOKEN_HEADER)
    @POST("auth/authorize")
    suspend fun authorize(@Body model: LoginRequestModel): AuthorizationResponse

    @Headers(com.arshapshap.wallette.core.network.data.NetworkConstants.NO_TOKEN_HEADER)
    @POST("auth/register")
    suspend fun register(@Body model: RegisterRequestModel): AuthorizationResponse
}