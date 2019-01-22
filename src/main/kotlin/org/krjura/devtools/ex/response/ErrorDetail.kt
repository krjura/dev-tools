package org.krjura.devtools.ex.response

data class ErrorDetail(
    val reason: String,
    val message: String?,
    val attributeName: String?,
    val attributeValues: List<String>
)
