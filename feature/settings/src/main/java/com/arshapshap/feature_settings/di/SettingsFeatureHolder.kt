package com.arshapshap.feature_settings.di

import com.arshapshap.common.di.FeatureApiHolder
import com.arshapshap.common.di.FeatureContainer
import com.arshapshap.common.di.scopes.ApplicationScope
import com.arshapshap.di.DataApi
import com.arshapshap.feature_settings.presentation.SettingsRouter
import javax.inject.Inject

@ApplicationScope
class SettingsFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val router: SettingsRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val settingsDependencies = DaggerSettingsComponent_SettingsDependenciesComponent.builder()
            .dataApi(getFeature(DataApi::class.java))
            .build()
        return DaggerSettingsComponent.builder()
            .router(router)
            .withDependencies(settingsDependencies)
            .build()
    }
}