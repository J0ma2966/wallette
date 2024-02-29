package com.arshapshap.feature_settings.domain

import com.arshapshap.common.domain.models.Category
import com.arshapshap.common.domain.repositories.CategoryRepository
import javax.inject.Inject

class SettingsCategoriesInteractor @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend fun createCategory(category: Category) {
        categoryRepository.createCategory(category)
    }

    suspend fun deleteCategory(category: Category) {
        categoryRepository.deleteCategory(category)
    }

    suspend fun editCategory(category: Category) {
        categoryRepository.updateCategory(category)
    }

    suspend fun getCategories(): List<Category> {
        return categoryRepository.getCategories()
    }}