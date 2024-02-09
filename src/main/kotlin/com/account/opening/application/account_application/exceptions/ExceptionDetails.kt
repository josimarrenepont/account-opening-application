package com.account.opening.application.account_application.exceptions

import java.time.LocalDateTime

data class ExceptionDetails(
    val title: String,
    val timestamp: LocalDateTime,
    val status: Int,
    val exception: String,
    val details: MutableMap<String, String?>
)