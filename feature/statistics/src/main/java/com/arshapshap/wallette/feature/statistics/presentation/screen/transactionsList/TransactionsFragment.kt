package com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList

import android.annotation.SuppressLint
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arshapshap.wallette.core.common.di.FeatureUtils
import com.arshapshap.wallette.core.common.domain.models.enums.Currency
import com.arshapshap.wallette.core.common.presentation.base.BaseFragment
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.feature.statistics.R
import com.arshapshap.wallette.feature.statistics.databinding.FragmentTransactionsListBinding
import com.arshapshap.wallette.feature.statistics.di.StatisticsComponent
import com.arshapshap.wallette.feature.statistics.di.StatisticsFeatureApi
import com.arshapshap.wallette.core.common.presentation.extensions.formatAsBalance
import com.arshapshap.wallette.core.common.presentation.extensions.getColorBySign
import com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.groupsRecyclerView.TransactionGroupsAdapter

class TransactionsFragment : BaseFragment<TransactionsViewModel>(R.layout.fragment_transactions_list) {

    companion object {
        const val PERIOD_START_KEY = "PERIOD_START_KEY"
        const val PERIOD_END_KEY = "PERIOD_END_KEY"
    }

    private val binding by viewBinding(FragmentTransactionsListBinding::bind)
    private val statisticsComponent: StatisticsComponent by lazy {
        FeatureUtils.getFeature(this, StatisticsFeatureApi::class.java)
    }

    override fun inject() {
        statisticsComponent.inject(this)
    }

    @Suppress("DEPRECATION")
    override fun createViewModel(): BaseViewModel
        = statisticsComponent.transactionsViewModel()
            .create(
                periodStart = arguments?.getSerializable(PERIOD_START_KEY) as? Date,
                periodEnd = arguments?.getSerializable(PERIOD_END_KEY) as? Date
            )

    @SuppressLint("NotifyDataSetChanged")
    override fun initViews() {
        with (binding) {
            listRecyclerView.adapter = TransactionGroupsAdapter {
                viewModel.openTransaction(it)
            }

            sortingImageButton.setOnClickListener {
                viewModel.changeSortingType()
                listRecyclerView.adapter?.notifyDataSetChanged()
            }

            refreshLayout.setOnRefreshListener {
                viewModel.loadData()

                refreshLayout.isRefreshing = false
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun subscribe() {
        with (viewModel) {
            loadData()
            stateLiveData.observe(viewLifecycleOwner) {
                (binding.listRecyclerView.adapter as TransactionGroupsAdapter).setList(it.groups, it.sortingType)

                val resId = when (it.sortingType) {
                    SortingType.ByCategory -> R.drawable.sort_by_categ
                    SortingType.ByTag -> R.drawable.sort_by_tag
                    else -> R.drawable.sort_by_date
                }
                binding.sortingImageButton.setImageDrawable(AppCompatResources.getDrawable(requireContext(), resId))

                binding.balanceTextView.text = it.balance.formatAsBalance(Currency.RUB)
                binding.balanceTextView.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        it.balance.getColorBySign()
                    )
                )
            }
        }
    }
}