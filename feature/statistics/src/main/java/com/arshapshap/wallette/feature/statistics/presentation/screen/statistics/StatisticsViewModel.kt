package com.arshapshap.wallette.feature.statistics.presentation.screen.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.wallette.core.common.domain.models.enums.Currency
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.core.common.presentation.extensions.between
import com.arshapshap.wallette.feature.statistics.domain.StatisticsInteractor
import com.arshapshap.wallette.feature.statistics.presentation.StatisticsRouter
import com.arshapshap.wallette.feature.statistics.domain.models.TransactionGroupByPeriod
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.util.*

class StatisticsViewModel @AssistedInject constructor(
    private val router: StatisticsRouter,
    private val interactor: StatisticsInteractor
) : BaseViewModel() {

    private val _dataLiveData = MutableLiveData<Data>()
    val dataLiveData: LiveData<Data>
        get() = _dataLiveData

    private val _openedPeriodIndexLiveData = MutableLiveData<Int>()
    val openedPeriodIndexLiveData: LiveData<Int>
        get() = _openedPeriodIndexLiveData

    fun openTransactions() {
        val openedPeriodIndex = _openedPeriodIndexLiveData.value ?: return
        val period = _dataLiveData.value?.items?.get(openedPeriodIndex) ?: return
        router.openTransactionsByPeriod(
            start = period.periodStart,
            end = period.periodEnd
        )
    }

    fun saveOpenedPeriod(index: Int) {
        _openedPeriodIndexLiveData.postValue(index)
    }

    fun loadData() {
        viewModelScope.launch {
            val groups = interactor.getTransactionsByPeriod()
            val balance = interactor.getBalance()
            val data = Data(
                items = groups,
                currency = interactor.getMainCurrency(),
                balance = balance
            )
            _dataLiveData.postValue(data)

            val now = Calendar.getInstance()
            groups.forEachIndexed { index, it ->
                if (now.time.between(it.periodStart, it.periodEnd)
                    || index == groups.size - 1
                ) {
                    _openedPeriodIndexLiveData.postValue(index)
                    return@forEachIndexed
                }
            }
        }
    }

    data class Data(
        val items: List<TransactionGroupByPeriod>,
        val currency: Currency,
        val balance: Double
    )

    @AssistedFactory
    interface Factory {

        fun create(): StatisticsViewModel
    }
}