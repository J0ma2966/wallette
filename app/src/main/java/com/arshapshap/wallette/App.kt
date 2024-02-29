package com.arshapshap.wallette

import android.app.Application
import com.arshapshap.common.di.CommonApi
import com.arshapshap.common.di.ComponentDependenciesProvider
import com.arshapshap.common.di.FeatureContainer
import com.arshapshap.common.di.HasComponentDependencies
import com.arshapshap.wallette.di.app.AppComponent
import com.arshapshap.wallette.di.app.FeatureHolderManager
import javax.inject.Inject

class App : Application(), FeatureContainer, HasComponentDependencies {

    @Inject
    lateinit var featureHolderManager: FeatureHolderManager

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

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

    override fun commonApi(): CommonApi {
        return appComponent
    }
}