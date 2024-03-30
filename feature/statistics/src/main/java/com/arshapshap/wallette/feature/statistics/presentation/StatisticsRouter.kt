package com.arshapshap.wallette.feature.statistics.presentation

import com.arshapshap.wallette.core.common.domain.models.Transaction
import java.util.Date

interface StatisticsRouter {

    fun openTransactions()

    fun openTransactionsByPeriod(start: Date?, end: Date?)

    fun openSingleTransaction(transaction: Transaction?)

    fun openStatistics()

    fun close()
}