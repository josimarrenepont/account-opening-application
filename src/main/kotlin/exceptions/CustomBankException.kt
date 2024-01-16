package exceptions

import java.time.LocalDateTime

open class CustomBankException(message: String) : RuntimeException(message){
    val exceptionDetails = ExceptionDetails(
        "Bank Exception",
        LocalDateTime.now(),
        500,
        this.javaClass.simpleName,
        mutableMapOf("message" to message)
    )
}
