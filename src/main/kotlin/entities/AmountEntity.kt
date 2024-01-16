// AmountEntity.kt
import entities.Account
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class AmountEntity(
    var value: BigDecimal = BigDecimal.ZERO,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    var account: Account? = null
)
