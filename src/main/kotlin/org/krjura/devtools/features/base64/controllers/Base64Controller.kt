package org.krjura.devtools.features.base64.controllers

import org.krjura.devtools.features.base64.services.Base64Service
import org.krjura.devtools.utils.ServerTimingBuilder
import org.krjura.devtools.utils.StopWatchUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class Base64Controller(private val base64Service: Base64Service) {

    @PostMapping(
        value = ["/api/v1/base64/decode"],
        consumes = [MediaType.TEXT_PLAIN_VALUE],
        produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE]
    )
    fun decodeBase64Bytes(
        @RequestBody request: ByteArray): ResponseEntity<ByteArray> {

        val (duration, result) = StopWatchUtils
            .execute { base64Service.decodeBytes(request) };

        return ResponseEntity(
            result,
            ServerTimingBuilder().addApp(duration).build(),
            HttpStatus.OK
        )
    }

    @PostMapping(
        value = ["/api/v1/base64/encode"],
        consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    fun encodeBase64Bytes(
        @RequestBody request: ByteArray): ResponseEntity<String> {

        val (duration, result) = StopWatchUtils
            .execute { base64Service.encodeBytes(request) };

        return ResponseEntity(
            result,
            ServerTimingBuilder().addApp(duration).build(),
            HttpStatus.OK
        )
    }
}