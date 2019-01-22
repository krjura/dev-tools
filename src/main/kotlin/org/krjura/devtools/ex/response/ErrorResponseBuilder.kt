package org.krjura.devtools.ex.response

import java.util.Objects

class ErrorResponseBuilder {

    private var details: MutableList<ErrorDetail> = ArrayList()

    fun detail(): ErrorDetailBuilder {
        return ErrorDetailBuilder(this)
    }

    fun addDetail(detail: ErrorDetail) {
        Objects.requireNonNull(detail)

        this.details.add(detail)
    }

    fun build(): ErrorResponse {
        return ErrorResponse(this.details)
    }
}

class ErrorDetailBuilder(private val parent: ErrorResponseBuilder) {

    private var reason: String = ""
    private var message: String? = null
    private var attributeName: String? = null
    private var attributeValues: MutableList<String> = ArrayList()

    fun reason(reason: String): ErrorDetailBuilder {
        Objects.requireNonNull(reason)

        this.reason = reason
        return this
    }

    fun attributeName(attributeName: String): ErrorDetailBuilder {
        Objects.requireNonNull(attributeName)

        this.attributeName = attributeName
        return this
    }

    fun message(message: String): ErrorDetailBuilder {
        Objects.requireNonNull(message)

        this.message = message
        return this
    }

    fun attributeValue(attributeValue: String?): ErrorDetailBuilder {
        if(attributeValue == null) {
            return this;
        }

        this.attributeValues.add(attributeValue)
        return this
    }

    fun attributeValue(attributeValues: MutableList<String>): ErrorDetailBuilder {
        Objects.requireNonNull(attributeValues)

        this.attributeValues = attributeValues
        return this
    }

    fun build(): ErrorResponseBuilder {
        Objects.requireNonNull(reason)

        this.parent.addDetail(ErrorDetail(reason, message, attributeName, attributeValues))
        return this.parent
    }
}