package ua.vashkulat.sequenceservice.exception.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ua.vashkulat.sequenceservice.exception.ApiError
import ua.vashkulat.sequenceservice.exception.MotifNotValidException
import java.sql.SQLIntegrityConstraintViolationException


@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handelMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val errors = exception.bindingResult.allErrors
            .joinToString(", ") { (it as FieldError).defaultMessage ?: "Unknown error" }
        return ResponseEntity.badRequest().body(
            ApiError(
                description = errors,
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        exception: ConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val errors = exception.constraintViolations
            .joinToString(", ") { it.message ?: "Unknown error" }

        return ResponseEntity.badRequest().body(
            ApiError(
                description = errors,
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(MotifNotValidException::class)
    fun handleMotifNotValidException(
        exception: MotifNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        return ResponseEntity.badRequest().body(
            ApiError(
                description = exception.message!!,
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleJdbcSQLIntegrityConstraintViolationException(
        exception: SQLIntegrityConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        return ResponseEntity.badRequest().body(
            ApiError(
                description = "Sequence name must be unique",
                path = request.requestURI
            )
        )
    }
}
