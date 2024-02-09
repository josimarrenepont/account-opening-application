package com.account.opening.application.account_application.exceptions

import java.time.LocalDateTime

open class CustomBankException(message: String) : RuntimeException(message){
    val exceptionDetails: ExceptionDetails = ExceptionDetails(
        "Bank Exception",
        LocalDateTime.now(),
        500,
        this.javaClass.simpleName,
        mutableMapOf("message" to message)
    )
}
