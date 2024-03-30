package com.arshapshap.wallette.feature.statistics.presentation.screen.statistics

import android.graphics.Color
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arshapshap.wallette.core.common.di.FeatureUtils
import com.arshapshap.wallette.core.common.domain.models.enums.TransactionType
import com.arshapshap.wallette.core.common.presentation.base.BaseFragment
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.core.common.presentation.extensions.formatAsBalance
import com.arshapshap.wallette.core.common.presentation.extensions.formatDayToString
import com.arshapshap.wallette.core.common.presentation.extensions.formatMonthToString
import com.arshapshap.wallette.core.common.presentation.extensions.formatYearToString
import com.arshapshap.wallette.core.common.presentation.extensions.getColorOnBackground
import com.arshapshap.wallette.feature.statistics.R
import com.arshapshap.wallette.feature.statistics.databinding.FragmentStatisticsBinding
import com.arshapshap.wallette.feature.statistics.di.StatisticsComponent
import com.arshapshap.wallette.feature.statistics.di.StatisticsFeatureApi
import com.arshapshap.wallette.feature.statistics.domain.models.TransactionGroupByPeriod
import com.arshapshap.wallette.feature.statistics.domain.models.TransactionShortInfo
import com.arshapshap.wallette.feature.statistics.presentation.screen.statistics.viewPager2.PieChartAdapter
import com.arshapshap.wallette.feature.statistics.presentation.screen.statistics.viewPager2.PieChartItem
import com.arshapshap.wallette.feature.statistics.presentation.screen.statistics.viewPager2.PieChartPeriod
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Calendar
import java.util.Date

class StatisticsFragment : BaseFragment<StatisticsViewModel>(R.layout.fragment_statistics) {

    private val binding by viewBinding(FragmentStatisticsBinding::bind)
    private val statisticsComponent: StatisticsComponent by lazy {
        FeatureUtils.getFeature(this, StatisticsFeatureApi::class.java)
    }

    override fun inject() {
        statisticsComponent.inject(this)
    }

    override fun createViewModel(): BaseViewModel =
        statisticsComponent.statisticsViewModel().create()

    override fun initViews() {
        with(binding) {
            balanceButtonLayout.balanceButton.setOnClickListener {
                viewModel.openTransactions()
            }

            balanceButtonLayout.arrowUpButton.setOnClickListener {
                viewModel.openTransactions()
            }

            balanceButtonLayout.changeViewButton.setOnClickListener {
            }

            refreshLayout.setOnRefreshListener {
                viewModel.loadData()

                refreshLayout.isRefreshing = false
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun subscribe() {
        viewModel.loadData()
        viewModel.dataLiveData.observe(viewLifecycleOwner) {
            binding.pieChartsViewPager2.adapter = PieChartAdapter(
                currency = it.currency,
                legendTextColor = getColorOnBackground(),
                incomeTextColor = resources.getColor(com.arshapshap.wallette.core.common.R.color.green),
                expenseTextColor = resources.getColor(com.arshapshap.wallette.core.common.R.color.red)
            )

            TabLayoutMediator(binding.periodsTabLayout, binding.pieChartsViewPager2) { tab, position ->
                tab.text = getPeriodString(it.items[position].periodStart, it.items[position].periodEnd)
            }.attach()
            getPieChartAdapter().setDataSet(it.items.map { transactionsToPieChartPeriod(it) })

            binding.balanceButtonLayout.balanceButton.text = getString(
                R.string.balance_number,
                it.balance.formatAsBalance(
                    currency = it.currency,
                    withPlus = false
                )
            )
        }
        viewModel.openedPeriodIndexLiveData.observe(viewLifecycleOwner) {
            val tab = binding.periodsTabLayout.getTabAt(it)
            if (tab?.isSelected == false)
                tab.select()

            binding.periodsTabLayout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let { viewModel.saveOpenedPeriod(tab.position) }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) { }

                override fun onTabReselected(tab: TabLayout.Tab?) { }
            })
        }
    }

    private fun transactionsToPieChartPeriod(period: TransactionGroupByPeriod): PieChartPeriod {
        var items = period.list.take(CATEGORIES_ON_CHART_NUMBER).map {
            PieChartItem(
                amount = it.amount.toFloat() * -1,
                name = getCategoryName(it),
                color = it.category?.icon?.colorInt
                    ?: Color.LTGRAY // TODO: ставить цвет по умолчанию (в зависимости от темы)
            )
        }
        if (period.list.count() > CATEGORIES_ON_CHART_NUMBER)
            items = items.plus(
                PieChartItem(
                    amount = period.list.drop(CATEGORIES_ON_CHART_NUMBER).sumOf { it.amount }.toFloat() * -1,
                    name = getString(R.string.other),
                    color = Color.GRAY // TODO: поставить цвет
                )
            )

        val result = PieChartPeriod(
            items = items,
            income = period.income,
            expense = period.expense
        )
        return result
    }

    private fun getCategoryName(transaction: TransactionShortInfo): String {
        if (transaction.type != TransactionType.Transfer)
            return transaction.category?.name ?: getString(R.string.no_category)
        else
            return getString(R.string.transfer_category)
    }

    private fun getPieChartAdapter(): PieChartAdapter
        = binding.pieChartsViewPager2.adapter as PieChartAdapter

    private fun getPeriodString(start: Date?, end: Date?): String {
        if (start == null || end == null) return getString(com.arshapshap.wallette.core.common.R.string.all)

        if (start == end) return start.formatDayToString()

        val startCalendar = Calendar.getInstance().apply {
            time = start
        }
        val endCalendar = Calendar.getInstance().apply {
            time = end
        }

        if (checkIfCoversOneMonth(startCalendar, endCalendar)) return start.formatMonthToString()

        if (checkIfCoversOneYear(startCalendar, endCalendar)) return start.formatYearToString()

        return "${startCalendar.time.formatDayToString()} - ${endCalendar.time.formatDayToString()}"
    }

    private fun checkIfCoversOneMonth(firstDate: Calendar, secondDate: Calendar): Boolean {
        val startYear = firstDate.get(Calendar.YEAR)
        val endYear = secondDate.get(Calendar.YEAR)
        val startMonth = firstDate.get(Calendar.MONTH)
        val endMonth = secondDate.get(Calendar.MONTH)
        val startDay = firstDate.get(Calendar.DAY_OF_MONTH)
        val endDay = secondDate.get(Calendar.DAY_OF_MONTH)

        return startYear == endYear && startMonth == endMonth &&
                startDay == 1 && endDay == secondDate.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun checkIfCoversOneYear(firstDate: Calendar, secondDate: Calendar): Boolean {
        val startYear = firstDate.get(Calendar.YEAR)
        val endYear = secondDate.get(Calendar.YEAR)
        val startMonth = firstDate.get(Calendar.MONTH)
        val endMonth = secondDate.get(Calendar.MONTH)
        val startDay = firstDate.get(Calendar.DAY_OF_MONTH)
        val endDay = secondDate.get(Calendar.DAY_OF_MONTH)

        return startYear == endYear && startMonth == Calendar.JANUARY && endMonth == Calendar.DECEMBER &&
                startDay == 1 && endDay == 31
    }

    companion object {
        private const val CATEGORIES_ON_CHART_NUMBER = 10
    }
}