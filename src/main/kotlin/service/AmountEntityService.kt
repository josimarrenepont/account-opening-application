package service
import AmountEntity
import AmountNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import repository.AmountEntityRepository
import java.math.BigDecimal

@Service
class AmountEntityService(
    @Autowired
    private val amountEntityRepository: AmountEntityRepository
) {

    fun save(amount: BigDecimal): AmountEntity {
        val amountEntity = AmountEntity(value = amount, amount = 100.0)
        return amountEntityRepository.save(amountEntity)
    }

    fun findById(id: Long): AmountEntity {
        return amountEntityRepository.findById(id).orElseThrow { throw AmountNotFoundException("Amount not found") }
    }

    fun getAllAmount(): List<AmountEntity> {
        return amountEntityRepository.findAll()
    }

    fun getAmountEntity(amountId: Long): AmountEntity? {
        return amountEntityRepository.getReferenceById(amountId)
    }
}
