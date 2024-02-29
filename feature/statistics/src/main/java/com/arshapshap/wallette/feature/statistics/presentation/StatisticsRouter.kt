package com.arshapshap.wallette.feature.statistics.presentation

import com.arshapshap.wallette.core.common.domain.models.Transaction

interface StatisticsRouter {
    fun openTransactions()

    fun openSingleTransaction(transaction: Transaction?)

    fun openStatistics()

    fun close()

    fun refresh()
}