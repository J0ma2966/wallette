package com.arshapshap.feature_settings.presentation.screen.singleCategory

import com.arshapshap.common.domain.models.enums.CategoryIcon
import com.arshapshap.common.domain.models.enums.TransactionType

data class EditingCategory(
    val id: Long? = null,
    val name: String = "",
    val icon: CategoryIcon = CategoryIcon.Empty,
    val type: TransactionType? = null
)