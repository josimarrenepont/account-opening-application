package com.account.opening.application.account_application.entities// entities.AmountEntity.kt
import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "tb_amount")
data class AmountEntity(
    var value: BigDecimal = BigDecimal.ZERO,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    var account: Account? = null,
    val amount: Double
)


