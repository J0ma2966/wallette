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

    private val binding by viewBinding(FragmentTransactionsListBinding::bind)
    private val statisticsComponent: com.arshapshap.wallette.feature.statistics.di.StatisticsComponent by lazy {
        FeatureUtils.getFeature(this, com.arshapshap.wallette.feature.statistics.di.StatisticsFeatureApi::class.java)
    }

    override fun inject() {
        statisticsComponent.inject(this)
    }

    override fun createViewModel(): BaseViewModel
        = statisticsComponent.transactionsViewModel().create()

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
                viewModel.refresh()

                refreshLayout.isRefreshing = false
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun subscribe() {
        with (viewModel) {
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