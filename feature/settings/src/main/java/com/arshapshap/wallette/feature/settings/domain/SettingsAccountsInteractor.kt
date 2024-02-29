package com.arshapshap.wallette.feature.settings.domain

import com.arshapshap.wallette.core.common.domain.models.Account
import com.arshapshap.wallette.core.common.domain.repositories.AccountRepository
import javax.inject.Inject

class SettingsAccountsInteractor @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend fun createAccount(account: Account) {
        accountRepository.createAccount(account)
    }

    suspend fun editAccount(account: Account) {
        accountRepository.updateAccount(account)
    }

    suspend fun deleteAccount(account: Account) {
        accountRepository.deleteAccount(account)
    }

    suspend fun getAccounts(): List<Account> {
        return accountRepository.getAccounts()
    }
}