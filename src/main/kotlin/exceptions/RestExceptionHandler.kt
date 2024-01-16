package exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException::class)
    fun handleInsufficientBalanceException(ex: InsufficientBalanceException):
            ResponseEntity<ExceptionDetails> {
        val errorDetails = ExceptionDetails(
            "Insufficient Balance",
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            ex.javaClass.simpleName,
            mutableMapOf("message" to ex.message)
        )
        return ResponseEntity(
            errorDetails, HttpStatus.BAD_REQUEST
        )
    }
    @ExceptionHandler(AccountNotFoundException::class)
    fun handlerInsufficientBalanceException(ex: AccountNotFoundException):
            ResponseEntity<ExceptionDetails>{
        val errorDetails = ExceptionDetails(
            "Account Not Found",
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.javaClass.simpleName,
            mutableMapOf("message" to ex.message)

        )
        return ResponseEntity(
            errorDetails, HttpStatus.NOT_FOUND
        )
    }
    @ExceptionHandler(CustomBankException::class)
    fun handlerInsufficientBalanceException(ex: CustomBankException):
            ResponseEntity<ExceptionDetails>{
        val errorDetails = ExceptionDetails(
            "Bank Exception",
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.javaClass.simpleName,
            mutableMapOf("message" to ex.message)
        )
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}