package org.krjura.devtools.features.password.controllers

import org.krjura.devtools.features.password.controllers.pojo.PasswordRequest
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.krjura.devtools.features.password.controllers.pojo.PasswordResponse
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class PasswordControllerTest: TestBase() {

    @Test
    fun generatePassword() {
        val request = PasswordRequest(
            useCapitalLetters = true,
            useSmallLetters = true,
            useNumbers = true,
            useSpecial = true,
            characterCount = 20
        )

        val responseBody = getMockMvc()
            .perform(post("/api/v1/password/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
            )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, PasswordResponse::class.java)
        assertThat(response).isNotNull

        response.let {
            assertThat(response.password).containsPattern("[A-Za-z0-9#$]{20}")
        }
    }
}