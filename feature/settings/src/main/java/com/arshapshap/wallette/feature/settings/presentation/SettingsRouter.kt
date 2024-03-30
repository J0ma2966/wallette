package com.arshapshap.wallette.feature.settings.presentation

import com.arshapshap.wallette.core.common.domain.models.Account
import com.arshapshap.wallette.core.common.domain.models.Category
import com.arshapshap.wallette.core.common.domain.models.Tag

interface SettingsRouter {

    fun openAccounts()

    fun openCategories()

    fun openTags()

    fun openSingleAccount(account: Account? = null)

    fun openSingleCategory(category: Category? = null)

    fun openSingleTag(tag: Tag? = null)

    fun openTransferCreating()

    fun openAuthorizationPage()

    fun close()
}