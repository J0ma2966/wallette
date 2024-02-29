package com.arshapshap.wallette.feature.settings.presentation.screen.categories

import androidx.core.view.isGone
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arshapshap.wallette.core.common.di.FeatureUtils
import com.arshapshap.wallette.core.common.presentation.base.BaseFragment
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.core.common.presentation.extensions.*
import com.arshapshap.wallette.feature.settings.R
import com.arshapshap.wallette.feature.settings.databinding.FragmentCategoriesBinding
import com.arshapshap.wallette.feature.settings.di.SettingsComponent
import com.arshapshap.wallette.feature.settings.di.SettingsFeatureApi
import com.arshapshap.wallette.feature.settings.presentation.screen.categories.recyclerView.CategoriesAdapter

class CategoriesFragment : BaseFragment<CategoriesViewModel>(R.layout.fragment_categories) {

    private val binding by viewBinding(FragmentCategoriesBinding::bind)
    private val component: SettingsComponent by lazy {
        FeatureUtils.getFeature(this, SettingsFeatureApi::class.java)
    }

    override fun inject() {
        component.inject(this)
    }

    override fun createViewModel(): BaseViewModel {
        return component.categoriesViewModel().create()
    }

    override fun initViews() {
        with (binding.addCategoryLayout) {
            setStrokeVisibility(true)
            setRightArrowVisible(true)
            setColor(getColorPrimary())
            setImage(R.drawable.ic_plus_box)
            setTitle(R.string.add_category)
            setOnClickListener {
                viewModel.openCategoryCreating()
            }
        }

        with (binding) {
            listIncomesRecyclerView.adapter = CategoriesAdapter {
                viewModel.openCategory(it)
            }
            listExpensesRecyclerView.adapter = CategoriesAdapter {
                viewModel.openCategory(it)
            }
        }

        binding.expensesTextView.isGone = true
        binding.incomesTextView.isGone = true
    }

    override fun subscribe() {
        viewModel.listIncomesLiveData.observe(viewLifecycleOwner) {
            getIncomesCategoriesAdapter()?.setList(it)
            if (it.isNotEmpty())
                binding.incomesTextView.isGone = false
        }
        viewModel.listExpensesLiveData.observe(viewLifecycleOwner) {
            getExpensesCategoriesAdapter()?.setList(it)
            if (it.isNotEmpty())
                binding.expensesTextView.isGone = false
        }
    }

    private fun getIncomesCategoriesAdapter()
        = (binding.listIncomesRecyclerView.adapter as? CategoriesAdapter)

    private fun getExpensesCategoriesAdapter()
        = (binding.listExpensesRecyclerView.adapter as? CategoriesAdapter)
}