package service

import com.account.opening.application.account_application.entities.Account
import com.account.opening.application.account_application.exceptions.InsufficientBalanceException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import com.account.opening.application.account_application.repository.AccountRepository
import com.account.opening.application.account_application.service.AccountService
import java.math.BigDecimal

class AccountServiceTest {

    @Mock
    private lateinit var accountRepository: AccountRepository

    @InjectMocks
    private lateinit var accountService: AccountService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testDeposit() {
        // Mocking data
        val accountId = 1L
        val initialBalance = BigDecimal(100)
        val depositAmount = BigDecimal(50)
        val account = Account(id = accountId, holderName = "Jose", balance = initialBalance)

        `when`(accountRepository.findById(accountId)).thenReturn(java.util.Optional.of(account))
        `when`(accountRepository.save(account)).thenReturn(account)

        // Calling the method
        val result = accountService.deposit(accountId, depositAmount)

        // Assertions
        assertEquals(accountId, result.id)
        assertEquals(initialBalance + depositAmount, result.balance)
    }

    @Test
    fun testWithdrawWithInsufficientBalance() {
        // Mocking data
        val accountId = 1L
        val initialBalance = BigDecimal(100)
        val withdrawAmount = BigDecimal(150)
        val account = Account(id = accountId, holderName = "Jose", balance = initialBalance)

        `when`(accountRepository.findById(accountId)).thenReturn(java.util.Optional.of(account))

        // Calling the method and expecting an exception
        assertThrows(InsufficientBalanceException::class.java) {
            accountService.withdraw(accountId, withdrawAmount)
        }
    }

    // Add more tests for other service methods...
}
