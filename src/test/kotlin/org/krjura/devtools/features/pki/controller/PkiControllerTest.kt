package org.krjura.devtools.features.pki.controller

import org.krjura.devtools.features.pki.controller.pojo.GeneratePairRequest
import org.krjura.devtools.features.pki.controller.pojo.GeneratePairResponse
import org.krjura.devtools.features.pki.enums.PkiAlgorithm
import org.krjura.devtools.features.pki.enums.PkiKeySizes
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.FluxExchangeResult
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

import org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test

class PkiControllerTest: TestBase() {

    @Test
    fun generateRsa512KeyPair() {
        val request = GeneratePairRequest(PkiKeySizes.L_512, PkiAlgorithm.RSA);

        val response: FluxExchangeResult<GeneratePairResponse> = webClient
            .post()
            .uri("/api/v1/pki/generate-pair")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), GeneratePairRequest::class.java)
            .exchange()
            .expectStatus().isOk
            .returnResult(GeneratePairResponse::class.java);

        val responseBody = response.responseBody.toMono().block();
        assertThat(responseBody).isNotNull

        responseBody?.let {
            assertThat(responseBody.algorithm).isEqualTo(request.algorithm);
            assertThat(responseBody.keySize).isEqualTo(request.keySize);
            assertThat(responseBody.private).contains("-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----")
            assertThat(responseBody.public).contains("-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----")
        }
    }
}