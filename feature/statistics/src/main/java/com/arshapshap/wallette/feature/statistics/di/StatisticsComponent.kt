package com.arshapshap.wallette.feature.statistics.di

import com.arshapshap.wallette.core.common.di.scopes.StatisticsScope
import com.arshapshap.wallette.feature.statistics.presentation.StatisticsRouter
import com.arshapshap.wallette.feature.statistics.presentation.screen.singleTransaction.SingleTransactionFragment
import com.arshapshap.wallette.feature.statistics.presentation.screen.singleTransaction.SingleTransactionViewModel
import com.arshapshap.wallette.feature.statistics.presentation.screen.statistics.StatisticsFragment
import com.arshapshap.wallette.feature.statistics.presentation.screen.statistics.StatisticsViewModel
import com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.TransactionsFragment
import com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.TransactionsViewModel
import dagger.BindsInstance
import dagger.Component

@StatisticsScope
@Component(
    dependencies = [com.arshapshap.wallette.feature.statistics.di.StatisticsDependencies::class]
)
interface StatisticsComponent : com.arshapshap.wallette.feature.statistics.di.StatisticsFeatureApi {

    @Component.Builder
    interface Builder {
        fun build(): com.arshapshap.wallette.feature.statistics.di.StatisticsComponent

        fun withDependencies(deps: com.arshapshap.wallette.feature.statistics.di.StatisticsDependencies): com.arshapshap.wallette.feature.statistics.di.StatisticsComponent.Builder

        @BindsInstance
        fun router(router: StatisticsRouter): com.arshapshap.wallette.feature.statistics.di.StatisticsComponent.Builder
    }

    @Component(
        dependencies = [
            com.arshapshap.wallette.core.data.di.DataApi::class
        ]
    )
    interface StatisticsDependenciesComponent :
        com.arshapshap.wallette.feature.statistics.di.StatisticsDependencies

    fun inject(statisticsFragment: StatisticsFragment)

    fun statisticsViewModel(): StatisticsViewModel.Factory

    fun inject(transactionsFragment: TransactionsFragment)

    fun transactionsViewModel(): TransactionsViewModel.Factory

    fun inject(fragment: SingleTransactionFragment)

    fun singleTransactionViewModel(): SingleTransactionViewModel.Factory
}