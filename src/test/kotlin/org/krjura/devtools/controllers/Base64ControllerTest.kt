package org.krjura.devtools.controllers

import org.junit.Test
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.charset.StandardCharsets

import org.assertj.core.api.Assertions.assertThat;

class Base64ControllerTest : TestBase() {

    @Test
    fun testBase64Decode() {

        val request:ByteArray = "dGVzdGRhdGE=".toByteArray(StandardCharsets.UTF_8);

        val result: MvcResult = mockMvc.perform(
            post("/api/v1/base64/decode")
                .content(request)
                .contentType(MediaType.APPLICATION_OCTET_STREAM))
            .andExpect(status().isOk)
            .andReturn()

        assertThat(result.response.contentAsByteArray).isEqualTo(byteArrayOf(116, 101, 115, 116, 100, 97, 116, 97))
    }

    @Test
    fun testBase64Encode() {

        val request:ByteArray = byteArrayOf(116, 101, 115, 116, 100, 97, 116, 97);

        val result: MvcResult = mockMvc.perform(
            post("/api/v1/base64/encode")
                .content(request)
                .contentType(MediaType.APPLICATION_OCTET_STREAM))
            .andExpect(status().isOk)
            .andReturn()

        assertThat(result.response.contentAsByteArray).isEqualTo("dGVzdGRhdGE=".toByteArray(StandardCharsets.UTF_8))
    }
}