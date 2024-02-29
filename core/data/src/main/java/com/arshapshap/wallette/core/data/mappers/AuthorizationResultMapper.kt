package com.arshapshap.wallette.core.data.mappers

import com.arshapshap.wallette.core.common.domain.models.network.AuthorizationResult
import com.arshapshap.wallette.core.network.data.models.response.AuthorizationResponse
import javax.inject.Inject

class AuthorizationResultMapper @Inject constructor() {

    fun map(response: AuthorizationResponse): AuthorizationResult {
        return with(response) {
            AuthorizationResult(
                isSuccessful = isSuccessful ?: false,
                token = token ?: "",
                errorMessage = errorMessage ?: ""
            )
        }
    }
}