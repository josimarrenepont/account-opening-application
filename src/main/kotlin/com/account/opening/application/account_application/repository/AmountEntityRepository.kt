package com.account.opening.application.account_application.repository

import com.account.opening.application.account_application.entities.AmountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AmountEntityRepository : JpaRepository<AmountEntity, Long>
