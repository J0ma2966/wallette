package com.arshapshap.wallette.core.data.repositories

import com.arshapshap.wallette.core.common.data.TokenManager
import com.arshapshap.wallette.core.common.domain.models.network.AuthorizationResult
import com.arshapshap.wallette.core.common.domain.repositories.AuthorizationRepository
import com.arshapshap.wallette.core.network.data.models.request.LoginRequestModel
import com.arshapshap.wallette.core.network.data.models.request.RegisterRequestModel
import com.arshapshap.wallette.core.network.data.services.AuthorizationApiService
import com.arshapshap.wallette.core.data.mappers.AuthorizationResultMapper
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val service: AuthorizationApiService,
    private val mapper: AuthorizationResultMapper,
    private val tokenManager: TokenManager
) : AuthorizationRepository {

    override suspend fun login(email: String, password: String): AuthorizationResult {
        val requestModel = LoginRequestModel(
            email = email, password = password
        )
        val response = service.authorize(
            model = requestModel
        )
        if (response.isSuccessful == true)
            tokenManager.saveAuthorizationToken(response.token!!)
        return mapper.map(response)
    }

    override suspend fun register(email: String, password: String): AuthorizationResult {
        val requestModel = RegisterRequestModel(
            email = email, password = password
        )
        val response = service.register(
            model = requestModel
        )
        if (response.isSuccessful == true)
            tokenManager.saveAuthorizationToken(response.token!!)
        return mapper.map(response)
    }

    override suspend fun checkIsAuthorized(): Boolean {
        return tokenManager.getAuthorizationToken() != null
    }

    override suspend fun logout() {
        tokenManager.deleteToken()
    }
}