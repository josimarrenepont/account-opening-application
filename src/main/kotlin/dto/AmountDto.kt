package dto
import java.math.BigDecimal

data class AmountDto(
    var amount: BigDecimal
) {
    val value: BigDecimal
        get() {
            TODO()
        }
}
