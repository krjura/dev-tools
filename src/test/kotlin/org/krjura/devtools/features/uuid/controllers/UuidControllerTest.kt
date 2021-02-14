package org.krjura.devtools.features.uuid.controllers

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.krjura.devtools.features.uuid.controllers.pojo.UuidWebResponse
import org.krjura.devtools.support.TestBase
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UuidControllerTest: TestBase() {

    @Test
    fun generateUuid() {
        val responseBody = getMockMvc()
            .perform(get("/api/v1/password/generate"))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, UuidWebResponse::class.java)
        Assertions.assertThat(response).isNotNull

        response.let {
            Assertions.assertThat(response.uuid).containsPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
        }
    }
}