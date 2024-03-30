package com.arshapshap.wallette.feature.settings.di

import com.arshapshap.wallette.core.common.data.SettingsManager
import com.arshapshap.wallette.core.common.domain.repositories.AccountRepository
import com.arshapshap.wallette.core.common.domain.repositories.AuthorizationRepository
import com.arshapshap.wallette.core.common.domain.repositories.CategoryRepository
import com.arshapshap.wallette.core.common.domain.repositories.TagRepository

interface SettingsDependencies {

    fun accountsRepository(): AccountRepository

    fun categoriesRepository(): CategoryRepository

    fun tagsRepository(): TagRepository

    fun authorizationRepository(): AuthorizationRepository

    fun settingsManager(): SettingsManager
}