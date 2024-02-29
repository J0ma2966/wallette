package com.arshapshap.wallette.core.common.data

interface TokenManager {

    fun getAuthorizationToken(): String?

    fun saveAuthorizationToken(token: String)

    fun deleteToken()

    fun isAuthorized(): Boolean
}