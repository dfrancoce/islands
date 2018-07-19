package com.islands.controller

import com.islands.response.ErrorResponse
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import java.time.LocalDateTime
import javax.validation.ConstraintViolationException


/**
 * This class handles the app exceptions
 */
@RestControllerAdvice
class IslandsOnMapErrorController {
    companion object : KLogging()

    @ExceptionHandler(value = [(ConstraintViolationException::class)])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun constraintViolationException(ex: ConstraintViolationException): ErrorResponse {
        logger.error { "IslandsOnMapErrorController - constraintViolationException - $ex" }
        return ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.message)
    }

    @ExceptionHandler(value = [(NoHandlerFoundException::class)])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun noHandlerFoundException(ex: Exception): ErrorResponse {
        logger.error { "IslandsOnMapErrorController - noHandlerFoundException - $ex" }
        return ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.message)
    }

    @ExceptionHandler(value = [(Exception::class)])
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unknownException(ex: Exception): ErrorResponse {
        logger.error { "IslandsOnMapErrorController - unknownException - $ex" }
        return ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, ex.message)
    }
}