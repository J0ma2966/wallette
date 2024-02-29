package com.arshapshap.wallette.di.app

import com.arshapshap.common.di.FeatureApiHolder
import com.arshapshap.common.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class FeatureManagerModule {

    @ApplicationScope
    @Provides
    fun provideFeatureHolderManager(featureApiHolderMap: @JvmSuppressWildcards Map<Class<*>, FeatureApiHolder>): FeatureHolderManager {
        return FeatureHolderManager(featureApiHolderMap)
    }
}