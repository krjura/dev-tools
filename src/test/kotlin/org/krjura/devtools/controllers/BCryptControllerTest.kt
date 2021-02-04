package org.krjura.devtools.controllers

import org.krjura.devtools.enums.CustomHeaders
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import reactor.core.publisher.Mono
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.krjura.devtools.ex.response.ErrorResponse
import org.krjura.devtools.features.bcrypt.pojo.BCryptWebRequest
import org.krjura.devtools.features.bcrypt.pojo.BCryptWebResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import reactor.core.publisher.toMono

class BCryptControllerTest: TestBase() {

    @Test
    fun testSimpleEncode() {

        val request = BCryptWebRequest(10, "password");

        val response = webClient
            .post()
            .uri("/api/v1/bcrypt/password")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), BCryptWebRequest::class.java)
            .exchange()
            .expectStatus().isOk
            .expectHeader().exists(CustomHeaders.SERVER_TIMING)
            .returnResult(BCryptWebResponse::class.java)


        val responseBody = response.responseBody.toMono().block();

        assertThat(responseBody).isNotNull

        responseBody?.let {
            assertThat(responseBody.iterations).isEqualTo(10)
            assertThat(responseBody.data).isEqualTo("password")
            assertThat(responseBody.encoded).isNotNull()
            assertThat(BCryptPasswordEncoder(10).matches("password", responseBody.encoded)).isTrue()
        }
    }

    @Test
    fun testSimpleEncodeUpperOutOfRange() {

        val request = BCryptWebRequest(20, "password");

        val response = webClient
            .post()
            .uri("/api/v1/bcrypt/password")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), BCryptWebRequest::class.java)
            .exchange()
            .expectStatus().isBadRequest
            .returnResult(ErrorResponse::class.java)

        val responseBody = response.responseBody.toMono().block();

        assertThat(responseBody).isNotNull

        responseBody?.let {
            assertThat(responseBody.details).hasSize(1)
            assertThat(responseBody.details.get(0).reason).isEqualTo("alerts.backend.constraints.class.BcryptIterationConstraint")
            assertThat(responseBody.details.get(0).message).isEqualTo("alerts.backend.constraints.class.BcryptIterationConstraint")
            assertThat(responseBody.details.get(0).attributeName).isEqualTo("iterations")
            assertThat(responseBody.details.get(0).attributeValues).hasSize(1)
            assertThat(responseBody.details.get(0).attributeValues.get(0)).isEqualTo("20")
        }
    }

    @Test
    fun testSimpleEncodeBottomOutOfRange() {

        val request = BCryptWebRequest(0, "password");

        val response = webClient
            .post()
            .uri("/api/v1/bcrypt/password")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), BCryptWebRequest::class.java)
            .exchange()
            .expectStatus().isBadRequest
            .returnResult(ErrorResponse::class.java)

        val responseBody = response.responseBody.toMono().block();

        assertThat(responseBody).isNotNull

        responseBody?.let {
            assertThat(responseBody.details).hasSize(1)
            assertThat(responseBody.details.get(0).reason).isEqualTo("alerts.backend.constraints.class.BcryptIterationConstraint")
            assertThat(responseBody.details.get(0).message).isEqualTo("alerts.backend.constraints.class.BcryptIterationConstraint")
            assertThat(responseBody.details.get(0).attributeName).isEqualTo("iterations")
            assertThat(responseBody.details.get(0).attributeValues).hasSize(1)
            assertThat(responseBody.details.get(0).attributeValues.get(0)).isEqualTo("0")
        }
    }

    @Test
    fun testSimpleEncodeEmptyData() {

        val request = BCryptWebRequest(10, "");

        val response = webClient
            .post()
            .uri("/api/v1/bcrypt/password")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(request), BCryptWebRequest::class.java)
            .exchange()
            .expectStatus().isBadRequest
            .returnResult(ErrorResponse::class.java)

        val responseBody = response.responseBody.toMono().block();

        assertThat(responseBody).isNotNull
        responseBody?.let {
            assertThat(responseBody.details).hasSize(1)
            assertThat(responseBody.details.get(0).reason).isEqualTo("alerts.backend.constraints.class.BCryptDataConstraint")
            assertThat(responseBody.details.get(0).message).isEqualTo("alerts.backend.constraints.class.BCryptDataConstraint")
            assertThat(responseBody.details.get(0).attributeName).isEqualTo("data")
            assertThat(responseBody.details.get(0).attributeValues).hasSize(1)
            assertThat(responseBody.details.get(0).attributeValues.get(0)).isEqualTo("")
        }
    }
}