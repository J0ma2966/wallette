package com.arshapshap.feature_auth.di

import com.arshapshap.common.domain.repositories.AuthorizationRepository

interface AuthorizationDependencies {

    fun authorizationRepository(): AuthorizationRepository
}