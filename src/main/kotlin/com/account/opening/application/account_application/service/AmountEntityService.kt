package com.account.opening.application.account_application.service
import com.account.opening.application.account_application.entities.AmountEntity
import com.account.opening.application.account_application.repository.AmountEntityRepository
import com.account.opening.application.account_application.exceptions.AmountNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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

    fun findAll(): List<AmountEntity> {
        return amountEntityRepository.findAll()
    }

    fun getAmountEntity(id: Long): AmountEntity? {
        return amountEntityRepository.getReferenceById(id)
    }
}
