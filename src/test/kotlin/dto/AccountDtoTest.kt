import dto.AccountDto
import entities.Account
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class AccountDtoTest {

    @Test
    fun testToEntity() {
        // Mocking data
        val accountDto = AccountDto(holderName = "Jose")

        // Calling the method
        val result = accountDto.toEntity()

        // Assertions
        assertEquals(accountDto.holderName, result.holderName)
        // Add more assertions as needed based on your mapping logic
    }

    private fun AccountDto(holderName: String): AccountDto {
        return dto.AccountDto(holderName, BigDecimal.ZERO)
    }
}
