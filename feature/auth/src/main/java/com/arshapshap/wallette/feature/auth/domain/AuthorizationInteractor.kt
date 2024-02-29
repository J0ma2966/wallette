package com.arshapshap.wallette.feature.auth.domain

import com.arshapshap.wallette.core.common.domain.models.network.AuthorizationResult
import com.arshapshap.wallette.core.common.domain.repositories.AuthorizationRepository
import javax.inject.Inject

class AuthorizationInteractor @Inject constructor(
    private val repository: AuthorizationRepository
) {

    suspend fun login(email: String, password: String): AuthorizationResult {
        return repository.login(email, password)
    }

    suspend fun register(email: String, password: String): AuthorizationResult {
        return repository.register(email, password)
    }
}