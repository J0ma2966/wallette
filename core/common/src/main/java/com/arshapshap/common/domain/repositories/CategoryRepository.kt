package com.arshapshap.common.domain.repositories

import com.arshapshap.common.domain.models.Category

interface CategoryRepository {

    suspend fun createCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    suspend fun getCategories(): List<Category>
}