package com.arshapshap.wallette.core.data.di

import com.arshapshap.wallette.core.common.di.FeatureApiHolder
import com.arshapshap.wallette.core.common.di.FeatureContainer
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.core.db.di.DbApi
import com.arshapshap.wallette.core.network.di.NetworkApi
import javax.inject.Inject

@ApplicationScope
class DataHolder @Inject constructor(
    featureContainer: FeatureContainer
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val dataDependencies = DaggerDataComponent_DataDependenciesComponent.builder()
            .commonApi(commonApi())
            .dbApi(getFeature(DbApi::class.java))
            .networkApi(getFeature(NetworkApi::class.java))
            .build()
        return DaggerDataComponent.builder()
            .withDependencies(dataDependencies)
            .build()
    }
}