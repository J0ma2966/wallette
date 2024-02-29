package com.arshapshap.feature_auth.di

import com.arshapshap.common.di.FeatureApiHolder
import com.arshapshap.common.di.FeatureContainer
import com.arshapshap.common.di.scopes.ApplicationScope
import com.arshapshap.di.DataApi
import com.arshapshap.feature_auth.presentation.screen.AuthorizationRouter
import javax.inject.Inject

@ApplicationScope
class AuthorizationFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val router: AuthorizationRouter,
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val authorizationDependencies = DaggerAuthorizationComponent_AuthorizationDependenciesComponent.builder()
            .dataApi(getFeature(DataApi::class.java))
            .build()
        return DaggerAuthorizationComponent.builder()
            .router(router)
            .withDependencies(authorizationDependencies)
            .build()
    }
}