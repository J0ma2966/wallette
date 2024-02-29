package com.arshapshap.wallette.feature.settings.presentation.screen.singleAccount

import com.arshapshap.wallette.core.common.domain.models.enums.AccountIcon
import com.arshapshap.wallette.core.common.domain.models.enums.Currency

data class EditingAccount(
    val id: Long = 0,
    val name: String = "",
    val icon: AccountIcon = AccountIcon.Empty,
    val startBalance: Double = 0.0,
    val currentBalance: Double = 0.0,
    val currency: Currency = Currency.RUB
)