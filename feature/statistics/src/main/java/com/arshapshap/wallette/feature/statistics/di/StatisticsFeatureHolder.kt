package com.arshapshap.wallette.feature.statistics.di

import com.arshapshap.wallette.core.common.di.FeatureApiHolder
import com.arshapshap.wallette.core.common.di.FeatureContainer
import com.arshapshap.wallette.core.common.di.scopes.ApplicationScope
import com.arshapshap.wallette.feature.statistics.presentation.StatisticsRouter
import javax.inject.Inject

@ApplicationScope
class StatisticsFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val statisticsRouter: StatisticsRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val statisticsDependencies = DaggerStatisticsComponent_StatisticsDependenciesComponent.builder()
            .dataApi(getFeature(com.arshapshap.wallette.core.data.di.DataApi::class.java))
            .build()
        return DaggerStatisticsComponent.builder()
            .router(statisticsRouter)
            .withDependencies(statisticsDependencies)
            .build()
    }
}