package com.account.opening.application.account_application.dto

import com.account.opening.application.account_application.entities.Account
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class AccountDto(
    @field:NotNull val holderName: String?,
    @field:NotNull val balance: BigDecimal?
) {
    fun toEntity(): Account {
        requireNotNull(holderName) { "Holder name cannot be null" }
        requireNotNull(balance) { "Balance cannot be null" }
        return Account(holderName = holderName, balance = balance)
    }
}
