// AmountService.kt
import org.springframework.stereotype.Service
import repositories.AmountRepository
import java.math.BigDecimal

@Service
class AmountService(private val amountRepository: AmountRepository) {

    fun save(amount: BigDecimal): AmountEntity {
        val amountEntity = AmountEntity(value = amount)
        return amountRepository.save(amountEntity)
    }

    fun findById(id: Long): AmountEntity {
        return amountRepository.findById(id).orElseThrow { throw AmountNotFoundException("Amount not found") }
    }

    fun getAllAmount(): List<AmountEntity> {
        return amountRepository.findAll()
    }

    fun getAmountEntity(amountId: Long): AmountEntity? {
        return amountRepository.getReferenceById(amountId)
    }
}
