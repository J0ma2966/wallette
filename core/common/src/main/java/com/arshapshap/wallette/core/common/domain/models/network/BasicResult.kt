package com.arshapshap.wallette.core.common.domain.models.network

data class BasicResult(
    val isSuccessful: Boolean,
    val errorMessage: String = ""
)