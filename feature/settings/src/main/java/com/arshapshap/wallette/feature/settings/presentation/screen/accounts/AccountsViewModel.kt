package com.arshapshap.wallette.feature.settings.presentation.screen.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.wallette.core.common.domain.models.Account
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.feature.settings.domain.SettingsAccountsInteractor
import com.arshapshap.wallette.feature.settings.presentation.SettingsRouter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class AccountsViewModel @AssistedInject constructor(
    private val interactor: SettingsAccountsInteractor,
    private val router: SettingsRouter
) : BaseViewModel() {

    private val _listLiveData = MutableLiveData<List<Account>>(listOf())
    val listLiveData: LiveData<List<Account>>
        get() = _listLiveData

    init {
        viewModelScope.launch {
            val list = interactor.getAccounts()
            _listLiveData.postValue(list)
        }
    }

    fun openAccountCreating() {
        router.openSingleAccount()
    }

    fun openTransferCreating() {
        router.openTransferCreating()
    }

    fun openAccount(account: Account) {
        router.openSingleAccount(account)
    }

    @AssistedFactory
    interface Factory {

        fun create(): AccountsViewModel
    }
}