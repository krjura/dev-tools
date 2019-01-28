package org.krjura.devtools.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.krjura.devtools.enums.CustomHeaders
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.FluxExchangeResult
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets

class Base64ControllerTest : TestBase() {

    @Test
    fun testBase64Decode() {

        val request:ByteArray = "dGVzdGRhdGE=".toByteArray(StandardCharsets.UTF_8);

        val result: FluxExchangeResult<ByteArray> = webClient
            .post()
            .uri("/api/v1/base64/decode")
            .contentType(MediaType.TEXT_PLAIN)
            .body(Mono.just(request), ByteArray::class.java)
            .exchange()
            .expectStatus().isOk
            .expectHeader().exists(CustomHeaders.SERVER_TIMING)
            .returnResult(ByteArray::class.java);

        assertThat(result.responseBodyContent).isEqualTo(byteArrayOf(116, 101, 115, 116, 100, 97, 116, 97))
    }


    @Test
    fun testBase64Encode() {

        val request:ByteArray = byteArrayOf(116, 101, 115, 116, 100, 97, 116, 97);

        val result: FluxExchangeResult<ByteArray> = webClient
            .post()
            .uri("/api/v1/base64/encode")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(Mono.just(request), ByteArray::class.java)
            .exchange()
            .expectStatus().isOk
            .expectHeader().exists(CustomHeaders.SERVER_TIMING)
            .returnResult(ByteArray::class.java);

        assertThat(result.responseBodyContent).isEqualTo("dGVzdGRhdGE=".toByteArray(StandardCharsets.UTF_8))
    }
}