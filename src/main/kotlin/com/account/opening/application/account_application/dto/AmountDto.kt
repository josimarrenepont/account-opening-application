package com.account.opening.application.account_application.dto

import java.math.BigDecimal

data class AmountDto(
    var amount: BigDecimal
) {
    val value: BigDecimal
        get() = amount
}
