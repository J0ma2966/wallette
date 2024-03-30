package com.arshapshap.wallette.core.data.repositories

import com.arshapshap.wallette.core.common.data.TokenManager
import com.arshapshap.wallette.core.common.domain.models.network.AuthorizationResult
import com.arshapshap.wallette.core.common.domain.repositories.AuthorizationRepository
import com.arshapshap.wallette.core.network.data.services.AuthorizationApiService
import com.arshapshap.wallette.core.data.mappers.AuthorizationResultMapper
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthorizationRepositoryImpl @Inject constructor(
    private val service: AuthorizationApiService,
    private val mapper: AuthorizationResultMapper,
    private val tokenManager: TokenManager
) : AuthorizationRepository {

    override suspend fun login(email: String, password: String): AuthorizationResult =
        suspendCoroutine { continuation ->
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    continuation.resume(
                        AuthorizationResult(
                            isSuccessful = it.isSuccessful,
                            errorMessage = it.exception?.message ?: "Error"
                        )
                    )
                }
        }

    override suspend fun register(email: String, password: String): AuthorizationResult =
        suspendCoroutine { continuation ->
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    continuation.resume(
                        AuthorizationResult(
                            isSuccessful = it.isSuccessful,
                            errorMessage = it.exception?.message ?: "Error"
                        )
                    )
                }
        }

    override suspend fun checkIsAuthorized(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

    override suspend fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}