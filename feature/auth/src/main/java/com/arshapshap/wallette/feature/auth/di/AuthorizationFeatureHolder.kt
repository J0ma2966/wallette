package com.arshapshap.wallette.feature.auth.di

import com.arshapshap.wallette.core.common.di.FeatureApiHolder
import com.arshapshap.wallette.core.common.di.FeatureContainer
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.feature.auth.presentation.screen.AuthorizationRouter
import javax.inject.Inject

@ApplicationScope
class AuthorizationFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val router: AuthorizationRouter,
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val authorizationDependencies = DaggerAuthorizationComponent_AuthorizationDependenciesComponent.builder()
            .dataApi(getFeature(com.arshapshap.wallette.core.data.di.DataApi::class.java))
            .build()
        return DaggerAuthorizationComponent.builder()
            .router(router)
            .withDependencies(authorizationDependencies)
            .build()
    }
}