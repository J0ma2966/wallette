package com.arshapshap.feature_settings.di

import com.arshapshap.common.data.SettingsManager
import com.arshapshap.common.domain.repositories.AccountRepository
import com.arshapshap.common.domain.repositories.AuthorizationRepository
import com.arshapshap.common.domain.repositories.CategoryRepository
import com.arshapshap.common.domain.repositories.TagRepository

interface SettingsDependencies {

    fun accountsRepository(): AccountRepository

    fun categoriesRepository(): CategoryRepository

    fun tagsRepository(): TagRepository

    fun authorizationRepository(): AuthorizationRepository

    fun settingsManager(): SettingsManager
}