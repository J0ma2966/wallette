package com.arshapshap.wallette.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.arshapshap.wallette.core.common.domain.models.*
import com.arshapshap.wallette.core.common.domain.models.enums.TransactionType
import com.arshapshap.wallette.feature.auth.presentation.screen.AuthorizationRouter
import com.arshapshap.wallette.feature.settings.presentation.SettingsRouter
import com.arshapshap.wallette.feature.settings.presentation.screen.singleAccount.SingleAccountFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.singleCategory.SingleCategoryFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.singleTag.SingleTagFragment
import com.arshapshap.wallette.feature.statistics.presentation.StatisticsRouter
import com.arshapshap.wallette.feature.statistics.presentation.screen.singleTransaction.SingleTransactionFragment
import com.arshapshap.wallette.R
import com.arshapshap.wallette.presentation.MainRouter

class Navigator : MainRouter, StatisticsRouter, AuthorizationRouter, SettingsRouter {

    private var navController: NavController? = null
    private var activity: AppCompatActivity? = null

    fun attachNavController(navController: NavController, activity: AppCompatActivity) {
        this.navController = navController
        this.activity = activity
    }

    override fun openTransactions() {
        navController?.navigate(R.id.transactionsFragment)
    }

    override fun openTransactionsByPeriod(start: Date?, end: Date?) {
        navController?.navigate(
            resId = R.id.transactionsFragment,
            args = Bundle().apply {
                putSerializable(TransactionsFragment.PERIOD_START_KEY, start)
                putSerializable(TransactionsFragment.PERIOD_END_KEY, end)
            }
        )
    }

    override fun openLoginPage() {
        navController?.navigate(R.id.loginFragment)
    }

    override fun openRegisterPage() {
        navController?.navigate(R.id.registerFragment)
    }

    override fun openStatistics() {
        navController?.navigate(R.id.statisticsFragment)
    }

    override fun close() {
        navController?.popBackStack()
    }

    override fun openAccounts() {
        navController?.navigate(
            resId = R.id.accountsFragment,
            args = null,
            navOptions = NavOptions.Builder()
                .setPopUpTo(
                    destinationId = R.id.settingsFragment,
                    inclusive = false
                )
                .build()
        )
    }

    override fun openCategories() {
        navController?.navigate(
            resId = R.id.categoriesFragment,
            args = null,
            navOptions = NavOptions.Builder()
                .setPopUpTo(
                    destinationId = R.id.settingsFragment,
                    inclusive = false
                )
                .build()
        )
    }

    override fun openTags() {
        navController?.navigate(
            resId = R.id.tagsFragment,
            args = null,
            navOptions = NavOptions.Builder()
                .setPopUpTo(
                    destinationId = R.id.settingsFragment,
                    inclusive = false
                )
                .build()
        )
    }

    override fun openSingleAccount(account: Account?) {
        navController?.navigate(
            resId = R.id.singleAccountFragment,
            args = Bundle().apply {
                putSerializable(SingleAccountFragment.ACCOUNT_KEY, account)
            },
            navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
        )
    }

    override fun openSingleCategory(category: Category?) {
        navController?.navigate(
            resId = R.id.singleCategoryFragment,
            args = Bundle().apply {
                putSerializable(SingleCategoryFragment.CATEGORY_KEY, category)
            },
            navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
        )
    }

    override fun openSingleTag(tag: Tag?) {
        navController?.navigate(
            resId = R.id.singleTagFragment,
            args = Bundle().apply {
                putSerializable(SingleTagFragment.TAG_KEY, tag)
            },
            navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
        )
    }

    override fun openSingleTransaction(transaction: Transaction?) {
        navController?.navigate(
            resId = R.id.singleTransactionFragment,
            args = Bundle().apply {
                putSerializable(SingleTransactionFragment.TRANSACTION_KEY, transaction)
            },
            navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
        )
    }

    override fun openTransferCreating() {
        navController?.navigate(
            resId = R.id.singleTransactionFragment,
            args = Bundle().apply {
                putSerializable(SingleTransactionFragment.TRANSACTION_TYPE_KEY, TransactionType.Transfer)
            },
            navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
        )
    }

    override fun refresh(){
        val id = navController?.currentDestination?.id ?: return
        navController?.popBackStack(id,true)
        navController?.navigate(id)
    }
}