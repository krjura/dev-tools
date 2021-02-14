package org.krjura.devtools.features.hroib

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.krjura.devtools.features.hroib.controller.pojo.HrOibGenerateResponse
import org.krjura.devtools.features.hroib.controller.pojo.HrOibValidateResponse
import org.krjura.devtools.support.TestBase
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class HrOibControllerTest: TestBase() {

    @Test
    fun testOibGeneration() {
        val responseBody = getMockMvc()
            .perform(get("/api/v1/hroib/generate"))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, HrOibGenerateResponse::class.java)
        Assertions.assertThat(response).isNotNull

        response.let {
            Assertions.assertThat(response.oib).containsPattern("\\d{11}")
        }
    }

    @Test
    fun testValidOib() {
        val responseBody = getMockMvc()
            .perform(get("/api/v1/hroib/validate/51544971898"))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, HrOibValidateResponse::class.java)
        Assertions.assertThat(response).isNotNull

        response.let {
            Assertions.assertThat(response.valid).isTrue()
        }
    }

    @Test
    fun testInValidOib() {
        val responseBody = getMockMvc()
            .perform(get("/api/v1/hroib/validate/51544971891"))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray

        val response = fromJson(responseBody, HrOibValidateResponse::class.java)
        Assertions.assertThat(response).isNotNull

        response.let {
            Assertions.assertThat(response.valid).isFalse
        }
    }
}