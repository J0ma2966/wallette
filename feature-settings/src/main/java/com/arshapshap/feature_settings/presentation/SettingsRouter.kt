package com.arshapshap.feature_settings.presentation

import com.arshapshap.common.domain.models.Account
import com.arshapshap.common.domain.models.Category
import com.arshapshap.common.domain.models.Tag

interface SettingsRouter {

    fun openAccounts()

    fun openCategories()

    fun openTags()

    fun openSingleAccount(account: Account? = null)

    fun openSingleCategory(category: Category? = null)

    fun openSingleTag(tag: Tag? = null)

    fun openTransferCreating()

    fun openLoginPage()

    fun close()
}