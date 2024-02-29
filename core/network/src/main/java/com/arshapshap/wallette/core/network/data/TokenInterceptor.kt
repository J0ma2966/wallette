package com.arshapshap.wallette.core.network.data

import com.arshapshap.wallette.core.common.data.TokenManager
import com.arshapshap.wallette.core.network.data.NetworkConstants.NO_TOKEN_HEADER
import com.arshapshap.wallette.core.network.data.NetworkConstants.TOKEN_HEADER
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header(NO_TOKEN_HEADER) == null) {
            val token = tokenManager.getAuthorizationToken()
            if (!token.isNullOrEmpty()) {
                request = request.newBuilder().addHeader(TOKEN_HEADER, "Token $token").build()
            }
        }

        return chain.proceed(request)
    }
}