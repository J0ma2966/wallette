package com.arshapshap.data.repositories

import com.arshapshap.common.data.TokenManager
import com.arshapshap.common.domain.models.Account
import com.arshapshap.common.domain.repositories.AccountRepository
import com.arshapshap.core_db.dao.AccountDao
import com.arshapshap.data.managers.SyncQueueManager
import com.arshapshap.data.managers.enums.RequestType
import com.arshapshap.data.mappers.AccountMapper
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val localSource: AccountDao,
    private val tokenManager: TokenManager,
    private val syncQueueManager: SyncQueueManager,
    private val mapper: AccountMapper
): AccountRepository {

    override suspend fun createAccount(account: Account) {
        val local = mapper.map(account)
        val id = localSource.addAccount(local)

        if (!tokenManager.isAuthorized()) return

        syncQueueManager.addRequest(RequestType.Create, account.copy(id = id))
        syncQueueManager.trySynchronize()
    }

    override suspend fun updateAccount(account: Account) {
        val local = mapper.map(account)
        localSource.updateAccount(local)

        if (!tokenManager.isAuthorized()) return

        syncQueueManager.addRequest(RequestType.Update, account)
        syncQueueManager.trySynchronize()
    }

    override suspend fun deleteAccount(account: Account) {
        val local = mapper.map(account)
        localSource.deleteAccount(local)

        if (!tokenManager.isAuthorized()) return

        syncQueueManager.addRequest(RequestType.Delete, account)
        syncQueueManager.trySynchronize()
    }

    override suspend fun getAccounts(): List<Account> {
        val list = localSource.getAccounts()
        return list.map { mapper.map(it) }
    }

    override suspend fun getAccountById(id: Long): Account {
        val local = localSource.getAccountById(id)
        return mapper.map(local)
    }
}