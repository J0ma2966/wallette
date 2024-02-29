package com.arshapshap.wallette.core.data.managers.interfaces

import com.arshapshap.wallette.core.common.domain.models.Category
import com.arshapshap.wallette.core.common.domain.models.network.BasicResult

interface CategoryRemoteRepository {

    suspend fun createCategoryRemote(category: Category): BasicResult

    suspend fun updateCategoryRemote(category: Category): BasicResult

    suspend fun deleteCategoryRemote(category: Category): BasicResult
}