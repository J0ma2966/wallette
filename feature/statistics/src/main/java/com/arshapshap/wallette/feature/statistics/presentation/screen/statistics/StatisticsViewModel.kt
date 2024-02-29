package com.arshapshap.wallette.feature.statistics.presentation.screen.statistics

import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.feature.statistics.presentation.StatisticsRouter
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