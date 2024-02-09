package com.account.opening.application.account_application.repository

import com.account.opening.application.account_application.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Long>{
    fun save(entity: Account): Account
}

