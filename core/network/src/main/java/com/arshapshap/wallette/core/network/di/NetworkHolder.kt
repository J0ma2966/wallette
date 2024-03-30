package com.arshapshap.wallette.core.network.di

import com.arshapshap.wallette.core.common.di.FeatureApiHolder
import com.arshapshap.wallette.core.common.di.FeatureContainer
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class NetworkHolder @Inject constructor(
    featureContainer: FeatureContainer
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val networkDependencies = DaggerNetworkComponent_NetworkDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerNetworkComponent.builder()
            .networkDependencies(networkDependencies)
            .build()
    }
}