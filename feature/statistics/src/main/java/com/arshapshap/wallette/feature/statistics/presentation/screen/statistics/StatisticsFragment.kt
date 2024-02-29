package com.arshapshap.wallette.feature.statistics.presentation.screen.statistics

import by.kirich1409.viewbindingdelegate.viewBinding
import com.arshapshap.wallette.core.common.di.FeatureUtils
import com.arshapshap.wallette.core.common.presentation.base.BaseFragment
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.feature.statistics.R
import com.arshapshap.wallette.feature.statistics.databinding.FragmentStatisticsBinding
import com.arshapshap.wallette.feature.statistics.di.StatisticsComponent
import com.arshapshap.wallette.feature.statistics.di.StatisticsFeatureApi

class StatisticsFragment : BaseFragment<StatisticsViewModel>(R.layout.fragment_statistics) {

    private val binding by viewBinding(FragmentStatisticsBinding::bind)
    private val statisticsComponent: com.arshapshap.wallette.feature.statistics.di.StatisticsComponent by lazy {
        FeatureUtils.getFeature(this, com.arshapshap.wallette.feature.statistics.di.StatisticsFeatureApi::class.java)
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
        }
    }

    override fun subscribe() {
    }
}