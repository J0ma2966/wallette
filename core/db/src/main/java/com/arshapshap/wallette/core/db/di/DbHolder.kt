package com.arshapshap.wallette.core.db.di

import com.arshapshap.wallette.core.common.di.FeatureApiHolder
import com.arshapshap.wallette.core.common.di.FeatureContainer
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class DbHolder @Inject constructor(
    featureContainer: FeatureContainer
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val dbDependencies = DaggerDbComponent_DbDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerDbComponent.builder()
            .dbDependencies(dbDependencies)
            .build()
    }
}