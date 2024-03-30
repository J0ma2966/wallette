package com.arshapshap.wallette.feature.settings.presentation.screen.settings

import by.kirich1409.viewbindingdelegate.viewBinding
import com.arshapshap.wallette.core.common.di.FeatureUtils
import com.arshapshap.wallette.core.common.presentation.base.BaseFragment
import com.arshapshap.wallette.core.common.presentation.base.BaseViewModel
import com.arshapshap.wallette.core.common.presentation.dialogs.PickerFragment
import com.arshapshap.wallette.core.common.presentation.dialogs.PickerFragment.Companion.showPickerDialog
import com.arshapshap.wallette.core.common.presentation.extensions.*
import com.arshapshap.wallette.feature.settings.R
import com.arshapshap.wallette.feature.settings.databinding.FragmentSettingsBinding
import com.arshapshap.wallette.feature.settings.di.SettingsComponent
import com.arshapshap.wallette.feature.settings.di.SettingsFeatureApi


class SettingsFragment : BaseFragment<SettingsViewModel>(R.layout.fragment_settings),
    PickerFragment.OnSelectListener {

    private val binding by viewBinding(FragmentSettingsBinding::bind)
    private val component: SettingsComponent by lazy {
        FeatureUtils.getFeature(this, SettingsFeatureApi::class.java)
    }

    override fun inject() {
        component.inject(this)
    }

    override fun createViewModel(): BaseViewModel {
        return component.settingsViewModel().create()
    }

    override fun initViews() {
        with (binding.enableSyncButton) {
            setColor(getColorOnPrimary())
            setFillColor(getColorPrimary())
            setImage(R.drawable.ic_sync)
            setTitle(R.string.enable_synchronization)
            setOnClickListener {
                viewModel.enableSynchronization()
            }
        }

        with (binding.goToAccountsButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(com.arshapshap.wallette.core.common.R.drawable.ic_account)
            setTitle(R.string.accounts_fragment_name)
            setOnClickListener {
                viewModel.openAccounts()
            }
        }

        with (binding.goToCategoriesButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(com.arshapshap.wallette.core.common.R.drawable.ic_category)
            setTitle(R.string.categories_fragment_name)
            setOnClickListener {
                viewModel.openCategories()
            }
        }

        with (binding.goToTagsButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(com.arshapshap.wallette.core.common.R.drawable.ic_tag)
            setTitle(R.string.tags_fragment_name)
            setOnClickListener {
                viewModel.openTags()
            }
        }

        with (binding.selectMainCurrencyButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(R.drawable.ic_currency)
            setTitle(R.string.main_currency)
        }

        with (binding.selectLanguageButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(R.drawable.ic_language)
            setTitle(R.string.language)
        }

        with (binding.selectFirstDayOfWeekButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(R.drawable.ic_calendar_today)
            setTitle(R.string.first_day_of_week)
        }

        with (binding.selectFirstDayOfMonthButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(R.drawable.ic_calendar_month)
            setTitle(R.string.first_day_of_month)
        }

        with (binding.selectTimePeriodButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(R.drawable.ic_calendar_note)
            setTitle(R.string.time_period)
        }

        with (binding.selectViewedAccountButton) {
            setStrokeVisibility(false)
            setRightArrowVisible(true)
            setImage(com.arshapshap.wallette.core.common.R.drawable.ic_account)
            setTitle(R.string.viewed_account)
        }
    }

    override fun subscribe() {
        with (viewModel) {
            isSynchronized.observe(viewLifecycleOwner) {
                with (binding.enableSyncButton) {
                    invalidate()
                    setImage(R.drawable.ic_sync)
                }

                if (it)
                    with (binding.enableSyncButton) {
                        setColor(getColorPrimary())
                        setStrokeVisibility(true)
                        setTitle(R.string.synchronization_enabled)
                        setOnClickListener {
                            viewModel.disableSynchronization()
                        }
                    }
                else
                    with (binding.enableSyncButton) {
                        setColor(getColorOnPrimary())
                        setFillColor(getColorPrimary())
                        setTitle(R.string.enable_synchronization)
                        setOnClickListener {
                            viewModel.enableSynchronization()
                        }
                    }
            }

            settingsLiveData.observe(viewLifecycleOwner) {
                with (binding.selectMainCurrencyButton) {
                    setValue(it.currency.name)
                    setOnClickListener {
                        showPickerDialog(
                            fragmentManager = childFragmentManager,
                            title = getString(R.string.main_currency),
                            items = it.availableCurrencies.map { it.name }.toTypedArray(),
                            tag = CURRENCY_PICKER_TAG
                        )
                    }
                }

                with (binding.selectLanguageButton) {
                    setValue(it.language.stringRes)
                    setOnClickListener {
                        showPickerDialog(
                            fragmentManager = childFragmentManager,
                            title = getString(R.string.language),
                            items = it.availableLanguages.map { getString(it.stringRes) }.toTypedArray(),
                            tag = LANGUAGE_PICKER_TAG
                        )
                    }
                }

                with (binding.selectFirstDayOfWeekButton) {
                    setValue(it.firstDayOfWeek.stringRes)
                    setOnClickListener {
                        showPickerDialog(
                            fragmentManager = childFragmentManager,
                            title = getString(R.string.first_day_of_week),
                            items = it.daysOfWeek.map { getString(it.stringRes) }.toTypedArray(),
                            tag = FIRST_DAY_OF_WEEK_PICKER_TAG
                        )
                    }
                }

                with (binding.selectFirstDayOfMonthButton) {
                    setValue(it.firstDayOfMonth.toString())
                    setOnClickListener {
                        showPickerDialog(
                            fragmentManager = childFragmentManager,
                            title = getString(R.string.first_day_of_month),
                            items = it.daysOfMonth.map { it.toString() }.toTypedArray(),
                            tag = FIRST_DAY_OF_MONTH_PICKER_TAG
                        )
                    }
                }

                with (binding.selectTimePeriodButton) {
                    setValue(it.timePeriod.stringRes)
                    setOnClickListener {
                        showPickerDialog(
                            fragmentManager = childFragmentManager,
                            title = getString(R.string.time_period),
                            items = it.availableTimePeriods.map { getString(it.stringRes) }.toTypedArray(),
                            tag = TIME_PERIOD_PICKER_TAG
                        )
                    }
                }

                with (binding.selectViewedAccountButton) {
                    if (it.viewedAccount == null) {
                        setValue(R.string.all_accounts)
                        setImage(com.arshapshap.wallette.core.common.R.drawable.ic_account)
                    } else {
                        setValue(it.viewedAccount.name)
                        setImage(it.viewedAccount.icon.drawableRes)
                    }
                    setOnClickListener {
                        showPickerDialog(
                            fragmentManager = childFragmentManager,
                            title = getString(R.string.viewed_account),
                            items = it.accounts
                                .map { it.name }
                                .plus( getString(R.string.all_accounts) )
                                .toTypedArray(),
                            tag = VIEWED_ACCOUNT_PICKER_TAG
                        )
                    }
                }
            }
        }
    }

    override fun onSelect(tag: String?, index: Int) {
        when (tag) {
            CURRENCY_PICKER_TAG -> onCurrencySelected(index)
            LANGUAGE_PICKER_TAG -> onLanguageSelected(index)
            FIRST_DAY_OF_WEEK_PICKER_TAG -> onFirstDayOfWeekSelected(index)
            FIRST_DAY_OF_MONTH_PICKER_TAG -> onFirstDayOfMonthSelected(index)
            TIME_PERIOD_PICKER_TAG -> onTimePeriodSelected(index)
            VIEWED_ACCOUNT_PICKER_TAG -> onViewedAccountSelected(index)
            else -> return
        }
    }

    private fun onCurrencySelected(index: Int) {
        val currency = viewModel.settingsLiveData.value!!.availableCurrencies[index]
        viewModel.selectCurrency(currency)
    }

    private fun onLanguageSelected(index: Int) {
        val language = viewModel.settingsLiveData.value!!.availableLanguages[index]
        viewModel.selectLanguage(language)
    }

    private fun onFirstDayOfWeekSelected(index: Int) {
        val firstDayOfWeek = viewModel.settingsLiveData.value!!.daysOfWeek[index]
        viewModel.selectFirstDayOfWeek(firstDayOfWeek)
    }

    private fun onFirstDayOfMonthSelected(index: Int) {
        val firstDayOfMonth = viewModel.settingsLiveData.value!!.daysOfMonth[index]
        viewModel.selectFirstDayOfMonth(firstDayOfMonth)
    }

    private fun onTimePeriodSelected(index: Int) {
        val timePeriod = viewModel.settingsLiveData.value!!.availableTimePeriods[index]
        viewModel.selectTimePeriod(timePeriod)
    }

    private fun onViewedAccountSelected(index: Int) {
        val account =
            if (index >= viewModel.settingsLiveData.value!!.accounts.count())
                null
            else
                viewModel.settingsLiveData.value!!.accounts[index]
        viewModel.selectViewedAccount(account)
    }

    companion object {

        private const val CURRENCY_PICKER_TAG = "currency"
        private const val LANGUAGE_PICKER_TAG = "language"
        private const val FIRST_DAY_OF_WEEK_PICKER_TAG = "first_day_of_week"
        private const val FIRST_DAY_OF_MONTH_PICKER_TAG = "first_day_of_month"
        private const val TIME_PERIOD_PICKER_TAG = "time_period"
        private const val VIEWED_ACCOUNT_PICKER_TAG = "viewed_account"
    }
}