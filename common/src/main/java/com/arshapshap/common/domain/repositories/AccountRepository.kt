package com.arshapshap.common.domain.repositories

import com.arshapshap.common.domain.models.Account

interface AccountRepository {

    suspend fun createAccount(account: Account)

    suspend fun updateAccount(account: Account)

    suspend fun deleteAccount(account: Account)

    suspend fun getAccounts(): List<Account>

    suspend fun getAccountById(id: Long): Account
}