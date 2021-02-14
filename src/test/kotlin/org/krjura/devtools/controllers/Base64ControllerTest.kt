package org.krjura.devtools.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.charset.StandardCharsets

class Base64ControllerTest : TestBase() {

    @Test
    fun testBase64Decode() {

        val request:ByteArray = "dGVzdGRhdGE=".toByteArray(StandardCharsets.UTF_8)

        val result: ByteArray = getMockMvc()
            .perform(post("/api/v1/base64/decode")
                .contentType(MediaType.TEXT_PLAIN)
                .content(request)
            )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray

        assertThat(result).isEqualTo(byteArrayOf(116, 101, 115, 116, 100, 97, 116, 97))
    }

    @Test
    fun testBase64Encode() {

        val request:ByteArray = byteArrayOf(116, 101, 115, 116, 100, 97, 116, 97)

        val result: ByteArray = getMockMvc()
            .perform(post("/api/v1/base64/encode")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .content(request)
            )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray

        assertThat(result).isEqualTo("dGVzdGRhdGE=".toByteArray(StandardCharsets.UTF_8))
    }
}