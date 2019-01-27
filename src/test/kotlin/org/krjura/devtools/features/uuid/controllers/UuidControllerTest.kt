package org.krjura.devtools.features.uuid.controllers

import org.assertj.core.api.Assertions
import org.junit.Test
import org.krjura.devtools.features.uuid.controllers.pojo.UuidWebResponse
import org.krjura.devtools.support.TestBase
import org.springframework.test.web.reactive.server.FluxExchangeResult
import reactor.core.publisher.toMono

class UuidControllerTest: TestBase() {

    @Test
    fun generateUuid() {
        val response: FluxExchangeResult<UuidWebResponse> = webClient
            .get()
            .uri("/api/v1/password/generate")
            .exchange()
            .expectStatus().isOk
            .returnResult(UuidWebResponse::class.java);

        val responseBody = response.responseBody.toMono().block();
        Assertions.assertThat(responseBody).isNotNull

        responseBody?.let {
            Assertions.assertThat(responseBody.uuid).containsPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
        }
    }
}