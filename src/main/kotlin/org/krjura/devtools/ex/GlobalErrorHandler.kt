package org.krjura.devtools.ex

import org.krjura.devtools.ex.response.ErrorResponse
import org.krjura.devtools.ex.response.ErrorResponseBuilder
import org.springframework.web.bind.annotation.ControllerAdvice
import java.util.ArrayList
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun handleWebExchangeBindException(ex: MethodArgumentNotValidException): ErrorResponse {
        val builder = ErrorResponseBuilder()

        val fieldErrors = ArrayList(ex.bindingResult.fieldErrors)
        fieldErrors.sortWith(compareBy { it.field })

        for (fieldError in fieldErrors) {
            builder
                .detail()
                .message(fieldError.defaultMessage!!) // need default message source
                .reason(fieldError.defaultMessage!!)
                .attributeName(fieldError.field)
                .attributeValue(getObjectValue(fieldError.rejectedValue))
                .build()
        }

        return builder.build()
    }

    private fun getObjectValue(value: Any?): String? {
        return value?.toString()
    }
}