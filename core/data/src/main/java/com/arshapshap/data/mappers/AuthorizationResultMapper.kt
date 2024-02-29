package com.arshapshap.data.mappers

import com.arshapshap.common.domain.models.network.AuthorizationResult
import com.arshapshap.core_network.data.models.response.AuthorizationResponse
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