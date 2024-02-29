package com.arshapshap.wallette.core.network.data.managers

import android.content.SharedPreferences
import com.arshapshap.wallette.core.common.data.TokenManager
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences
) : com.arshapshap.wallette.core.common.data.TokenManager {

    override fun getAuthorizationToken(): String?
        = sharedPrefs.getString(TOKEN_KEY, null)

    override fun saveAuthorizationToken(token: String)
        = sharedPrefs.edit().putString(TOKEN_KEY, token).apply()

    override fun deleteToken()
        = sharedPrefs.edit().putString(TOKEN_KEY, null).apply()

    override fun isAuthorized(): Boolean
        = getAuthorizationToken() != null

    companion object {
        private const val TOKEN_KEY = "token"
    }
}