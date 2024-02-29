package com.arshapshap.wallette.feature.settings.di

import com.arshapshap.wallette.core.common.di.scopes.SettingsScope
import com.arshapshap.wallette.core.data.di.DataApi
import com.arshapshap.wallette.feature.settings.presentation.SettingsRouter
import com.arshapshap.wallette.feature.settings.presentation.screen.accounts.AccountsFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.accounts.AccountsViewModel
import com.arshapshap.wallette.feature.settings.presentation.screen.singleAccount.SingleAccountFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.singleAccount.SingleAccountViewModel
import com.arshapshap.wallette.feature.settings.presentation.screen.categories.CategoriesFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.categories.CategoriesViewModel
import com.arshapshap.wallette.feature.settings.presentation.screen.settings.SettingsFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.settings.SettingsViewModel
import com.arshapshap.wallette.feature.settings.presentation.screen.singleCategory.SingleCategoryFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.singleCategory.SingleCategoryViewModel
import com.arshapshap.wallette.feature.settings.presentation.screen.singleTag.SingleTagFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.singleTag.SingleTagViewModel
import com.arshapshap.wallette.feature.settings.presentation.screen.tags.TagsFragment
import com.arshapshap.wallette.feature.settings.presentation.screen.tags.TagsViewModel
import dagger.BindsInstance
import dagger.Component

@SettingsScope
@Component(
    dependencies = [
        SettingsDependencies::class
    ]
)
interface SettingsComponent : SettingsFeatureApi {

    @Component.Builder
    interface Builder {
        fun build(): SettingsComponent

        fun withDependencies(deps: SettingsDependencies): SettingsComponent.Builder

        @BindsInstance
        fun router(router: SettingsRouter): SettingsComponent.Builder
    }

    @Component(
        dependencies = [
            DataApi::class
        ]
    )
    interface SettingsDependenciesComponent :
        SettingsDependencies

    fun inject(fragment: SettingsFragment)

    fun settingsViewModel(): SettingsViewModel.Factory

    fun inject(fragment: AccountsFragment)

    fun accountsViewModel(): AccountsViewModel.Factory

    fun inject(fragment: CategoriesFragment)

    fun categoriesViewModel(): CategoriesViewModel.Factory

    fun inject(fragment: TagsFragment)

    fun tagsViewModel(): TagsViewModel.Factory

    fun inject(fragment: SingleAccountFragment)

    fun singleAccountViewModel(): SingleAccountViewModel.Factory

    fun inject(fragment: SingleCategoryFragment)

    fun singleCategoryViewModel(): SingleCategoryViewModel.Factory

    fun inject(fragment: SingleTagFragment)

    fun singleTagViewModel(): SingleTagViewModel.Factory
}