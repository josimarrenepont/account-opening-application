// AmountEntity.kt
import entities.Account
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "tb_amount")
data class AmountEntity(
    @Column(nullable = false) var value: BigDecimal = BigDecimal.ZERO,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    var account: Account? = null,
    val amount: Double
)
