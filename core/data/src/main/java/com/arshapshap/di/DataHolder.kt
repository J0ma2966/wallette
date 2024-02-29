package com.arshapshap.di

import com.arshapshap.common.di.FeatureApiHolder
import com.arshapshap.common.di.FeatureContainer
import com.arshapshap.common.di.scopes.ApplicationScope
import com.arshapshap.core_db.di.DbApi
import com.arshapshap.core_network.di.NetworkApi
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