package com.arshapshap.wallette.core.common.domain.models.network

data class AuthorizationResult(
    val isSuccessful: Boolean,
    val token: String = "",
    val errorMessage: String = ""
)
