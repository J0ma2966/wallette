package com.arshapshap.feature_statistics_impl.presentation.screen.statistics

import com.arshapshap.common.presentation.base.BaseViewModel
import com.arshapshap.feature_statistics_impl.presentation.StatisticsRouter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class StatisticsViewModel @AssistedInject constructor(
    private val router: StatisticsRouter
) : BaseViewModel() {

    fun openTransactions() {
        router.openTransactions()
    }

    @AssistedFactory
    interface Factory {

        fun create(): StatisticsViewModel
    }
}