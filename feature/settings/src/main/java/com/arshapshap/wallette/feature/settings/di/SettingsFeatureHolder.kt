package com.arshapshap.wallette.feature.settings.di

import com.arshapshap.wallette.core.common.di.FeatureApiHolder
import com.arshapshap.wallette.core.common.di.FeatureContainer
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.feature.settings.presentation.SettingsRouter
import javax.inject.Inject

@ApplicationScope
class SettingsFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val router: SettingsRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val settingsDependencies = DaggerSettingsComponent_SettingsDependenciesComponent.builder()
            .dataApi(getFeature(com.arshapshap.wallette.core.data.di.DataApi::class.java))
            .build()
        return DaggerSettingsComponent.builder()
            .router(router)
            .withDependencies(settingsDependencies)
            .build()
    }
}