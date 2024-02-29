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
    dependencies = [StatisticsDependencies::class]
)
interface StatisticsComponent : StatisticsFeatureApi {

    @Component.Builder
    interface Builder {
        fun build(): StatisticsComponent

        fun withDependencies(deps: StatisticsDependencies): Builder

        @BindsInstance
        fun router(router: StatisticsRouter): Builder
    }

    @Component(
        dependencies = [
            com.arshapshap.wallette.core.data.di.DataApi::class
        ]
    )
    interface StatisticsDependenciesComponent :
        StatisticsDependencies

    fun inject(statisticsFragment: StatisticsFragment)

    fun statisticsViewModel(): StatisticsViewModel.Factory

    fun inject(transactionsFragment: TransactionsFragment)

    fun transactionsViewModel(): TransactionsViewModel.Factory

    fun inject(fragment: SingleTransactionFragment)

    fun singleTransactionViewModel(): SingleTransactionViewModel.Factory
}