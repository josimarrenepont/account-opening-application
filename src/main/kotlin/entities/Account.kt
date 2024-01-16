package entities

import AmountEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "tb_account")
data class Account(
    @Column(nullable = false, unique = true) var holderName: String = "",
    @Column(nullable = false) var balance: BigDecimal = BigDecimal.ZERO,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,

    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var amounts: List<AmountEntity> = mutableListOf()
)




