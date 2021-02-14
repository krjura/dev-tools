package org.krjura.devtools.controllers

import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.krjura.devtools.ex.response.ErrorResponse
import org.krjura.devtools.features.bcrypt.pojo.BCryptWebRequest
import org.krjura.devtools.features.bcrypt.pojo.BCryptWebResponse
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class BCryptControllerTest: TestBase() {

    @Test
    fun testSimpleEncode() {

        val request = BCryptWebRequest(10, "password")

        val responseBody = getMockMvc()
            .perform(post("/api/v1/bcrypt/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
            )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, BCryptWebResponse::class.java)

        assertThat(response).isNotNull

        response.let {
            assertThat(response.iterations).isEqualTo(10)
            assertThat(response.data).isEqualTo("password")
            assertThat(response.encoded).isNotNull
            assertThat(BCryptPasswordEncoder(10).matches("password", response.encoded)).isTrue
        }
    }

    @Test
    fun testSimpleEncodeUpperOutOfRange() {

        val request = BCryptWebRequest(20, "password")

        val responseBody = getMockMvc()
            .perform(post("/api/v1/bcrypt/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
            )
            .andExpect(status().isBadRequest)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, ErrorResponse::class.java)

        assertThat(response).isNotNull

        response.let {
            assertThat(response.details).hasSize(1)
            assertThat(response.details[0].reason).isEqualTo("alerts.backend.constraints.class.BcryptIterationConstraint")
            assertThat(response.details[0].message).isEqualTo("alerts.backend.constraints.class.BcryptIterationConstraint")
            assertThat(response.details[0].attributeName).isEqualTo("iterations")
            assertThat(response.details[0].attributeValues).hasSize(1)
            assertThat(response.details[0].attributeValues[0]).isEqualTo("20")
        }
    }

    @Test
    fun testSimpleEncodeBottomOutOfRange() {

        val request = BCryptWebRequest(0, "password")

        val responseBody = getMockMvc()
            .perform(post("/api/v1/bcrypt/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
            )
            .andExpect(status().isBadRequest)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, ErrorResponse::class.java)

        assertThat(response).isNotNull

        response.let {
            assertThat(response.details).hasSize(1)
            assertThat(response.details[0].reason).isEqualTo("alerts.backend.constraints.class.BcryptIterationConstraint")
            assertThat(response.details[0].message).isEqualTo("alerts.backend.constraints.class.BcryptIterationConstraint")
            assertThat(response.details[0].attributeName).isEqualTo("iterations")
            assertThat(response.details[0].attributeValues).hasSize(1)
            assertThat(response.details[0].attributeValues[0]).isEqualTo("0")
        }
    }

    @Test
    fun testSimpleEncodeEmptyData() {

        val request = BCryptWebRequest(10, "")

        val responseBody = getMockMvc()
            .perform(post("/api/v1/bcrypt/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
            )
            .andExpect(status().isBadRequest)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, ErrorResponse::class.java)

        assertThat(response).isNotNull
        response.let {
            assertThat(response.details).hasSize(1)
            assertThat(response.details[0].reason).isEqualTo("alerts.backend.constraints.class.BCryptDataConstraint")
            assertThat(response.details[0].message).isEqualTo("alerts.backend.constraints.class.BCryptDataConstraint")
            assertThat(response.details[0].attributeName).isEqualTo("data")
            assertThat(response.details[0].attributeValues).hasSize(1)
            assertThat(response.details[0].attributeValues[0]).isEqualTo("")
        }
    }
}