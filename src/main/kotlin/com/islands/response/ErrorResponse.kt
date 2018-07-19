package com.islands.response

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
        val timestamp: LocalDateTime,
        val status: HttpStatus,
        val message: String?
)