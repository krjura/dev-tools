package org.krjura.devtools.features.password.controllers

import org.krjura.devtools.features.password.controllers.pojo.PasswordRequest
import org.krjura.devtools.features.password.controllers.pojo.PasswordResponse
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.FluxExchangeResult
import reactor.core.publisher.Mono

import org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test
import reactor.core.publisher.toMono

class PasswordControllerTest: TestBase() {

    @Test
    fun generatePassword() {
        val request = PasswordRequest(true, true, true, 20);

        val response: FluxExchangeResult<PasswordResponse> = webClient
            .post()
            .uri("/api/v1/password/generate")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), PasswordRequest::class.java)
            .exchange()
            .expectStatus().isOk
            .returnResult(PasswordResponse::class.java);

        val responseBody = response.responseBody.toMono().block();
        assertThat(responseBody).isNotNull

        responseBody?.let {
            assertThat(responseBody.password).containsPattern("[A-Za-z0-9]{20}")
        }
    }
}