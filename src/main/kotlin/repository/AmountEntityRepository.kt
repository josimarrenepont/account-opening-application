package repositories// repositories.AmountRepository.kt
import AmountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AmountRepository : JpaRepository<AmountEntity, Long>
