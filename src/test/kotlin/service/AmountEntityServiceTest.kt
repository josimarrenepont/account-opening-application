package service
import com.account.opening.application.account_application.entities.AmountEntity
import com.account.opening.application.account_application.repository.AmountEntityRepository
import com.account.opening.application.account_application.exceptions.AmountNotFoundException
import org.mockito.ArgumentMatchers.any
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.VerificationModeFactory.times
import org.mockito.junit.jupiter.MockitoExtension
import com.account.opening.application.account_application.service.AmountEntityService
import java.math.BigDecimal
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class AmountEntityServiceTest {

    @Mock
    private lateinit var amountEntityRepository: AmountEntityRepository

    @InjectMocks
    private lateinit var amountEntityService: AmountEntityService

    @Test
    fun testSaveAmountEntity() {
        // Configuração do cenário
        val inputAmount = BigDecimal("50.0")
        val expectedAmountEntity = AmountEntity(id = 1L, value = inputAmount, amount = 100.0)

        // Configuração do mock
        Mockito.`when`(amountEntityRepository.save(Mockito.any())).thenReturn(expectedAmountEntity)

        // Execução do método a ser testado
        val savedAmountEntity = amountEntityService.save(inputAmount)

        // Verificações
        assertNotNull(savedAmountEntity)
        assertEquals(expectedAmountEntity, savedAmountEntity)

        // Verifica se o método save foi chamado no repositório
        verify(amountEntityRepository, times(1)).save(any())

        // Use ArgumentCaptor se precisar verificar os detalhes do argumento passado
        val captor = ArgumentCaptor.forClass(AmountEntity::class.java)
        verify(amountEntityRepository).save(captor.capture())

        // Adicione assertivas com base no argumento capturado, se necessário
        assertEquals(inputAmount, captor.value.value)
        // Adicione mais assertivas conforme necessário
    }


    @Test
    fun testFindAmountEntityById() {
        val expectedAmountEntity = AmountEntity(id = 1L, value = BigDecimal("50.0"), amount = 100.0)

        `when`(amountEntityRepository.findById(1L)).thenReturn(Optional.of(expectedAmountEntity))

        val foundAmountEntity = amountEntityService.findById(1L)

        assertNotNull(foundAmountEntity)
        assertEquals(expectedAmountEntity, foundAmountEntity)
    }

    @Test
    fun testFindAmountEntityByIdNotFound() {
        `when`(amountEntityRepository.findById(1L)).thenReturn(Optional.empty())

        assertThrows<AmountNotFoundException> {
            amountEntityService.findById(1L)
        }
    }

    @Test
    fun testGetAllAmountEntities() {
        val expectedAmountEntities = listOf(
            AmountEntity(id = 1L, value = BigDecimal("50.0"), amount = 100.0),
            AmountEntity(id = 2L, value = BigDecimal("75.0"), amount = 150.0)
        )

        `when`(amountEntityRepository.findAll()).thenReturn(expectedAmountEntities)

        val allAmountEntities = amountEntityService.findAll()

        assertNotNull(allAmountEntities)
        assertEquals(expectedAmountEntities, allAmountEntities)
    }

    @Test
    fun testGetAmountEntity() {
        val expectedAmountEntity = AmountEntity(id = 1L, value = BigDecimal("50.0"), amount = 100.0)

        `when`(amountEntityRepository.getReferenceById(1L)).thenReturn(expectedAmountEntity)

        val amountEntity = amountEntityService.getAmountEntity(1L)

        assertNotNull(amountEntity)
        assertEquals(expectedAmountEntity, amountEntity)
    }
}
