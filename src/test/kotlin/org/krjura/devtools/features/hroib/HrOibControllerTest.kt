package org.krjura.devtools.features.hroib

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.krjura.devtools.features.hroib.controller.pojo.HrOibGenerateResponse
import org.krjura.devtools.features.hroib.controller.pojo.HrOibValidateResponse
import org.krjura.devtools.support.TestBase
import org.springframework.test.web.reactive.server.FluxExchangeResult
import reactor.core.publisher.toMono

class HrOibControllerTest: TestBase() {

    @Test
    fun testOibGeneration() {
        val response: FluxExchangeResult<HrOibGenerateResponse> = webClient
            .get()
            .uri("/api/v1/hroib/generate")
            .exchange()
            .expectStatus().isOk
            .returnResult(HrOibGenerateResponse::class.java);

        val responseBody = response.responseBody.toMono().block();
        Assertions.assertThat(responseBody).isNotNull

        responseBody?.let {
            Assertions.assertThat(responseBody.oib).containsPattern("\\d{11}")
        }
    }

    @Test
    fun testValidOib() {
        val response: FluxExchangeResult<HrOibValidateResponse> = webClient
            .get()
            .uri("/api/v1/hroib/validate/51544971898")
            .exchange()
            .expectStatus().isOk
            .returnResult(HrOibValidateResponse::class.java);

        val responseBody = response.responseBody.toMono().block();
        Assertions.assertThat(responseBody).isNotNull

        responseBody?.let {
            Assertions.assertThat(responseBody.valid).isTrue()
        }
    }

    @Test
    fun testInValidOib() {
        val response: FluxExchangeResult<HrOibValidateResponse> = webClient
            .get()
            .uri("/api/v1/hroib/validate/51544971891")
            .exchange()
            .expectStatus().isOk
            .returnResult(HrOibValidateResponse::class.java);

        val responseBody = response.responseBody.toMono().block();
        Assertions.assertThat(responseBody).isNotNull

        responseBody?.let {
            Assertions.assertThat(responseBody.valid).isFalse()
        }
    }
}