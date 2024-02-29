package com.arshapshap.wallette.feature.settings.presentation.screen.accounts

import by.kirich1409.viewbindingdelegate.viewBinding
import com.arshapshap.wallette.core.common.di.FeatureUtils
import com.arshapshap.wallette.core.common.presentation.base.BaseFragment
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.core.common.presentation.extensions.getColorPrimary
import com.arshapshap.wallette.core.common.presentation.extensions.*
import com.arshapshap.wallette.feature.settings.R
import com.arshapshap.wallette.feature.settings.databinding.FragmentAccountsBinding
import com.arshapshap.wallette.feature.settings.di.SettingsComponent
import com.arshapshap.wallette.feature.settings.di.SettingsFeatureApi
import com.arshapshap.wallette.feature.settings.presentation.screen.accounts.recyclerView.AccountsAdapter

class AccountsFragment : BaseFragment<AccountsViewModel>(R.layout.fragment_accounts) {

    private val binding by viewBinding(FragmentAccountsBinding::bind)
    private val component: SettingsComponent by lazy {
        FeatureUtils.getFeature(this, SettingsFeatureApi::class.java)
    }

    override fun inject() {
        component.inject(this)
    }

    override fun createViewModel(): BaseViewModel {
        return component.accountsViewModel().create()
    }

    override fun initViews() {
        with (binding.addAccountLayout) {
            setStrokeVisibility(true)
            setRightArrowVisible(true)
            setColor(getColorPrimary())
            setImage(R.drawable.ic_plus)
            setTitle(R.string.add_account)
            setOnClickListener {
                viewModel.openAccountCreating()
            }
        }

        binding.listRecyclerView.adapter = AccountsAdapter {
            viewModel.openAccount(it)
        }

        with (binding.addMoneyTransferLayout) {
            setStrokeVisibility(true)
            setRightArrowVisible(true)
            setColor(getColorPrimary())
            setImage(R.drawable.ic_transfer)
            setTitle(R.string.transfer_money)
            setOnClickListener {
                viewModel.openTransferCreating()
            }
        }
    }

    override fun subscribe() {
        viewModel.listLiveData.observe(viewLifecycleOwner) {
            getAccountsAdapter()?.setList(it)
            binding.addMoneyTransferLayout.setGone(it.count() < 2)
        }
    }

    private fun getAccountsAdapter()
        = (binding.listRecyclerView.adapter as? AccountsAdapter)
}