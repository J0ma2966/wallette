package com.arshapshap.feature_statistics_impl.di

import com.arshapshap.common.di.scopes.StatisticsScope
import com.arshapshap.di.DataApi
import com.arshapshap.feature_statistics_impl.presentation.StatisticsRouter
import com.arshapshap.feature_statistics_impl.presentation.screen.singleTransaction.SingleTransactionFragment
import com.arshapshap.feature_statistics_impl.presentation.screen.singleTransaction.SingleTransactionViewModel
import com.arshapshap.feature_statistics_impl.presentation.screen.statistics.StatisticsFragment
import com.arshapshap.feature_statistics_impl.presentation.screen.statistics.StatisticsViewModel
import com.arshapshap.feature_statistics_impl.presentation.screen.transactionsList.TransactionsFragment
import com.arshapshap.feature_statistics_impl.presentation.screen.transactionsList.TransactionsViewModel
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
            DataApi::class
        ]
    )
    interface StatisticsDependenciesComponent : StatisticsDependencies

    fun inject(statisticsFragment: StatisticsFragment)

    fun statisticsViewModel(): StatisticsViewModel.Factory

    fun inject(transactionsFragment: TransactionsFragment)

    fun transactionsViewModel(): TransactionsViewModel.Factory

    fun inject(fragment: SingleTransactionFragment)

    fun singleTransactionViewModel(): SingleTransactionViewModel.Factory
}