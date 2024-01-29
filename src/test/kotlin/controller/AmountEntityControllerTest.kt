import controller.AmountEntityController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.mockito.Mockito.`when`
import service.AmountEntityService

@ExtendWith(MockitoExtension::class)
class AmountEntityControllerTest {

    @Mock
    private lateinit var amountEntityService: AmountEntityService

    @InjectMocks
    private lateinit var amountEntityController: AmountEntityController

    @Test
    fun testFindAllAmount() {
        // Mocking the service response
        val mockedAmountEntities = listOf(AmountEntity(id = 1, amount = 100.0), AmountEntity(id = 2, amount = 200.0))
        `when`(amountEntityService.getAllAmount()).thenReturn(mockedAmountEntities)

        // Calling the controller method
        val responseEntity: ResponseEntity<List<AmountEntity>> = amountEntityController.findAllAmount()

        // Assertions
        assertNotNull(responseEntity)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(mockedAmountEntities, responseEntity.body)
    }

    @Test
    fun testFindByIdAmount() {
        // Mocking the service response
        val mockedAmountEntity = AmountEntity(id = 1, amount = 100.0)
        `when`(amountEntityService.getAmountEntity(1)).thenReturn(mockedAmountEntity)

        // Calling the controller method
        val responseEntity: ResponseEntity<AmountEntity> = amountEntityController.findByIdAmount(1)

        // Assertions
        assertNotNull(responseEntity)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(mockedAmountEntity, responseEntity.body)
    }
}
