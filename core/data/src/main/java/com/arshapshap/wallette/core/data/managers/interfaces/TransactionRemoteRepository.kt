package com.arshapshap.wallette.core.data.managers.interfaces

import com.arshapshap.wallette.core.common.domain.models.Transaction
import com.arshapshap.wallette.core.common.domain.models.network.BasicResult

interface TransactionRemoteRepository {

    suspend fun createTransactionRemote(transaction: Transaction): BasicResult

    suspend fun updateTransactionRemote(transaction: Transaction): BasicResult

    suspend fun deleteTransactionRemote(transaction: Transaction): BasicResult
}