package com.arshapshap.wallette

import android.app.Application
import com.arshapshap.wallette.core.common.di.CommonApi
import com.arshapshap.wallette.core.common.di.ComponentDependenciesProvider
import com.arshapshap.wallette.core.common.di.FeatureContainer
import com.arshapshap.wallette.core.common.di.HasComponentDependencies
import com.arshapshap.wallette.di.app.AppComponent
import com.arshapshap.wallette.di.app.FeatureHolderManager
import javax.inject.Inject

class App : Application(), FeatureContainer,
    com.arshapshap.wallette.core.common.di.HasComponentDependencies {

    @Inject
    lateinit var featureHolderManager: FeatureHolderManager

    @Inject
    override lateinit var dependencies: com.arshapshap.wallette.core.common.di.ComponentDependenciesProvider

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = AppComponent.init(this)
        appComponent.inject(this)
    }

    override fun <T> getFeature(key: Class<*>): T {
        return featureHolderManager.getFeature<T>(key)!!
    }

    override fun releaseFeature(key: Class<*>) {
        featureHolderManager.releaseFeature(key)
    }

    override fun commonApi(): com.arshapshap.wallette.core.common.di.CommonApi {
        return appComponent
    }
}