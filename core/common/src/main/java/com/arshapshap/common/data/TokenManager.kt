package com.arshapshap.common.data

interface TokenManager {

    fun getAuthorizationToken(): String?

    fun saveAuthorizationToken(token: String)

    fun deleteToken()

    fun isAuthorized(): Boolean
}