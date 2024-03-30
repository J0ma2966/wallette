package com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.transactionsRecyclerView

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.arshapshap.wallette.core.common.domain.models.Transaction
import com.arshapshap.wallette.core.common.presentation.extensions.formatAsBalance
import com.arshapshap.wallette.core.common.presentation.extensions.formatDayToString
import com.arshapshap.wallette.core.common.presentation.extensions.getColorBySign
import com.arshapshap.wallette.feature.statistics.R
import com.arshapshap.wallette.feature.statistics.databinding.ItemExpandableTransactionBinding
import com.arshapshap.wallette.feature.statistics.presentation.screen.transactionsList.SortingType

class TransactionsViewHolder(
    private val binding: ItemExpandableTransactionBinding,
    private val sortingType: SortingType,
    private val onClick: (Transaction) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(transaction: Transaction) {
        with (binding) {
            if (sortingType == SortingType.ByCategory)
                nameTextView.text = transaction.date.formatDayToString()
            else
                nameTextView.text = transaction.category?.name ?: binding.root.context.getString(R.string.no_category)
            groupCommentTextView.text = transaction.description

            groupPriceTextView.text = transaction.amount.formatAsBalance(transaction.account.currency)
            groupPriceTextView.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    transaction.amount.getColorBySign()
                )
            )

            binding.root.setOnClickListener {
                onClick.invoke(transaction)
            }
        }
    }
}