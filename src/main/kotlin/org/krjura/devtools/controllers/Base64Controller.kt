package org.krjura.devtools.controllers

import org.krjura.devtools.services.Base64Service
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class Base64Controller(private val base64Service: Base64Service) {

    companion object {
        val logger = LoggerFactory.getLogger(Base64Controller::class.java)
    }

    @PostMapping(
        value = ["/api/v1/base64/decode"],
        consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    fun decodeBase64Bytes(
        @RequestBody request: ByteArray): ResponseEntity<ByteArray> {

        return ResponseEntity.ok(base64Service.decodeBytes(request))
    }

    @PostMapping(
        value = ["/api/v1/base64/encode"],
        consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    fun encodeBase64Bytes(
        @RequestBody request: ByteArray): ResponseEntity<String> {

        return ResponseEntity.ok(base64Service.encodeBytes(request));
    }
}