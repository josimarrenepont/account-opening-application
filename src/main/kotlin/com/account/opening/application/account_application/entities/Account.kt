package com.account.opening.application.account_application.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "tb_account")
data class Account(
    @Column(nullable = false, unique = true) var holderName: String = "",
    @Column(nullable = false) var balance: BigDecimal = BigDecimal.ZERO,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,

    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    var amounts: List<AmountEntity> = mutableListOf()
)




