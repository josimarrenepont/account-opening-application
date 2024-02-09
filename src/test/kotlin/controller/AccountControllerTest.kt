package controller

import com.account.opening.application.account_application.dto.AccountDto
import com.account.opening.application.account_application.entities.Account
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.account.opening.application.account_application.service.AccountService
import com.account.opening.application.account_application.controller.AccountController

import java.math.BigDecimal

class AccountControllerTest {

    @Mock
    private lateinit var accountService: AccountService

    @InjectMocks
    private lateinit var accountController: AccountController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testFindAllAccounts() {
        // Mocking data
        val accounts = listOf(Account(id = 1, holderName = "Jose", balance = BigDecimal(100)))
        `when`(accountService.findAll()).thenReturn(accounts)

        // Calling the method
        val result: ResponseEntity<List<Account>> = accountController.findAll()

        // Assertions
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(accounts, result.body)
    }

    @Test
    fun testFindAccountById() {
        // Mocking data
        val accountId = 1L
        val account = Account(id = accountId, holderName = "Jose", balance = BigDecimal(100))
        `when`(accountService.findById(accountId)).thenReturn(account)

        // Calling the method
        val result: ResponseEntity<Account> = accountController.findById(accountId)

        // Assertions
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(account, result.body)
    }

    @Test
    fun testOpenAccount() {
        // Mocking data
        val accountDto = AccountDto(holderName = "Jose")
        val savedAccount = Account(id = 1, holderName = "Jose", balance = BigDecimal.ZERO)
        `when`(accountService.save(accountDto.toEntity())).thenReturn(savedAccount)

        // Calling the method
        val result: ResponseEntity<Account> = accountController.openAccount(accountDto)

        // Assertions
        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertNotNull(result.body) // VERIFICAR SE RESPOSTA NÃO É NULO
        assertEquals(savedAccount, result.body)

        // Log para depuração
        println("Expected Account: $savedAccount")
        println("Actual Account: ${result.body}")
    }

    private fun AccountDto(holderName: String): AccountDto {
     return AccountDto(holderName, BigDecimal.ZERO)
    }
}
