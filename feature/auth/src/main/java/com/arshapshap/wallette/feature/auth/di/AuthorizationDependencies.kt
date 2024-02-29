package com.arshapshap.wallette.feature.auth.di

import com.arshapshap.wallette.core.common.domain.repositories.AuthorizationRepository

interface AuthorizationDependencies {

    fun authorizationRepository(): AuthorizationRepository
}